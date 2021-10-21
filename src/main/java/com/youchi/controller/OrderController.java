package com.youchi.controller;


import com.youchi.mapper.GoodsMapper;
import com.youchi.mapper.OrderGoodsMapper;
import com.youchi.mapper.OrderMapper;
import com.youchi.mapper.UserMapper;
import com.youchi.model.Goods;
import com.youchi.model.Order;
import com.youchi.model.OrderGoods;
import com.youchi.model.User;
import com.youchi.responseMessage.ResponseMessage;
import com.youchi.vo.GoodsCartVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Order)controller层
 *
 * @author makejava
 * @since 2021-09-23 16:34:32
 */
@Api(tags = "订单")
@RestController
@RequestMapping("order")
public class OrderController  {
    /**
     * 服务对象
     */
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;
   /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("all/{size}/{num}")
    public ResponseMessage selectAll(@PathVariable(value = "size") int size,
                                     @PathVariable(value = "num") int num,
                                     @RequestHeader(required = false) String token
    ) {
     final List<Order> page1 = orderMapper.page((num - 1) * size, size);
        Map<String, Object> map = new HashMap<>();;
        map.put("records", page1);
        map.put("total", orderMapper.count());
        return ResponseMessage.success(map);
    }


 
    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseMessage selectOne(@PathVariable(value = "id") Long id) {
        return ResponseMessage.success(orderMapper.findById(id));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("add")
    public ResponseMessage insert(@RequestBody List<GoodsCartVo> goodsCartVos) {
        Float total = 0F;
        for (GoodsCartVo goodsCartVo : goodsCartVos) {
            if (goodsCartVo.getGoodsId() == null || goodsCartVo.getUserId() == null || goodsCartVo.getNum() <= 0) {
                return ResponseMessage.error("GoodsCartVo信息不对");
            }
            final Goods goods = goodsMapper.selectById(goodsCartVo.getGoodsId());
            if (goods.getInventory() < goodsCartVo.getNum()) {
                return ResponseMessage.error(goods.getName() + "库存不足");
            }
            Float price = goods.getPrice() * goodsCartVo.getNum();
            total += price;
        }
        final User user = userMapper.selectById(goodsCartVos.get(0).getUserId());
        if (total > user.getMoney()) {
            return ResponseMessage.error("余额不足");
        }
        user.setMoney(user.getMoney() - total);
        final Order order = new Order(goodsCartVos.get(0).getUserId());
        orderMapper.insert(order);
        for (GoodsCartVo goodsCartVo : goodsCartVos) {
            final Goods goods = goodsMapper.selectById(goodsCartVo.getGoodsId());
            goods.setInventory(goods.getInventory() - goodsCartVo.getNum());
            Float price = goods.getPrice() * goodsCartVo.getNum();
            final OrderGoods orderForGoods = new OrderGoods(goodsCartVo.getGoodsId(), goodsCartVo.getNum(), price, order.getId());
            goodsMapper.updateById(goods);
            orderGoodsMapper.insert(orderForGoods);
        }
        order.setTotalMoney(total);
        orderMapper.updateById(order);
        userMapper.updateById(user);
        return ResponseMessage.success(orderMapper.findOrderByOrderId(order.getId()));
    }
    /**
     * 修改数据
     *
     * 
     * @return 修改结果
     */
    @PostMapping("modify")
    public ResponseMessage update(@RequestBody Order order) {
        orderMapper.updateById(order);
        return ResponseMessage.error("ok");
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping("del/{id}")
    public ResponseMessage delete(@PathVariable(value = "id") Long id) {
        orderMapper.deleteById(id);
        return ResponseMessage.success("ok");
    }
}

