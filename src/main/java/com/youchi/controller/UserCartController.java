package com.youchi.controller;

import com.youchi.domain.UserCard;
import com.youchi.domain.UserCardManager;
import com.youchi.mapper.AdminMapper;
import com.youchi.mapper.GoodsMapper;
import com.youchi.model.Admin;
import com.youchi.model.Goods;
import com.youchi.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("ucc")
@ResponseBody
public class UserCartController {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    UserCardManager userCardManager;

    @Autowired
    GoodsMapper goodsmapper;

    @PostMapping("add/{goodsId}/{num}")
    public ResponseMessage addCard(@RequestHeader(required = false) String token,
//                                   @RequestBody Goods goods,
                                   @PathVariable(value = "goodsId") Long goodsId,
                                   @PathVariable(value = "num") Long num){

        final Admin token1 = adminMapper.findToken(token);
        if(token1==null){
            return ResponseMessage.error("-1");
        }
        final Goods goods = goodsmapper.findGoodsById(goodsId);
        if(goods==null){
            return ResponseMessage.error("not find goods");
        }
        final List<UserCard> userCards = UserCard.findUserCards(token1.getId());
        for(UserCard s :userCards){
            if(s.getGoods().getId()==goodsId){
                if(s.getNum()+num<=0){
                    userCardManager.map().remove(token1.getId());
                    return ResponseMessage.success("商品移出购物车");
                }
                s.setNum(s.getNum()+num);
                return ResponseMessage.success("ok");
            }
        }
        if(num<=0){
            return ResponseMessage.error("数量不能小于0");
        }
        UserCard userCard = new UserCard();
        userCard.setGoods(goods);
        userCard.setNum(num);
        userCards.add(userCard);
        userCardManager.map().put(token1.getId(),userCards);
        return ResponseMessage.success("ok");

    }
    @GetMapping("find")
    public ResponseMessage findCard(@RequestHeader(required = false) String token){
        final Admin token1 = adminMapper.findToken(token);
        if(token1==null){
            return ResponseMessage.error("-1");
        }
        return ResponseMessage.success(UserCardManager.map().get(token1.getId()));
    }


}
