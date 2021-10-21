package com.youchi.vo;

import lombok.Data;

@Data
public class StoreVo {
    private String name;
    private String address;
    private String intro ;//简介
    private long storeClassifyId;
//    private long merchantId;
    private String photo;
}
