package com.youchi.domain;

import com.youchi.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserCard {
    private Goods goods;
    private Long num;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public static List<UserCard> findUserCards(long id) {
        final UserCardManager userCardManager = UserCardManager.userCardManager();
//        UserCardManager.userCardManager().
        final List<UserCard> userCards2 = (List<UserCard>) userCardManager.map().get(id);
        List<UserCard> userCards= (List<UserCard>) UserCardManager.map().get(id);
        if(userCards==null){
            final List<UserCard> userCards1 = new ArrayList<>();
//            userCardManager.setUserCardList(userCards1);
            userCards = userCards1;
        }
        return userCards;
    }

    public List<UserCard> addCard(Goods goods,long num,List<UserCard> userCards){
//        final List<UserCard> userCards = UserCard.findUserCards(id);
        UserCard userCard = new UserCard();
        userCard.setGoods(goods);
        userCard.setNum(num);
        userCards.add(userCard);
        System.out.println(userCard);
        return userCards;
    }
}
