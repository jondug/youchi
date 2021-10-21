package com.youchi.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserCardManager {
    private UserCardManager() {}

    private  List<UserCard> userCardList = new ArrayList<>();

    private static class SingletonHolder {
        private static UserCardManager userCardManager = new UserCardManager();
        private static Map<Long, List<UserCard>> map = new HashMap<>();
    }

    public static UserCardManager userCardManager() {
        return SingletonHolder.userCardManager;
    }

    public static Map map() {
        return SingletonHolder.map;
    }

    public List<UserCard> getUserCardList() {
        return userCardList;
    }

    public void setUserCardList(List<UserCard> userCardList) {
        this.userCardList = userCardList;
    }


}
