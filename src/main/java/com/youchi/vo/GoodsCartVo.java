package com.youchi.vo;

import lombok.Data;

@Data
public class GoodsCartVo {
    private Long userId;
    private Long goodsId;
    private Long num;
    //小计
//    private Float subtotal;
}
