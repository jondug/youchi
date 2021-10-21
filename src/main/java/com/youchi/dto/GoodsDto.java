package com.youchi.dto;

import lombok.Data;

@Data
public class GoodsDto {
    private Long id;


    private String name;

    private Float price;

    private Float newPrice;
    private Float discount;
    //封面
    private String cover;
    //简介
    private String introduce;
    //库存
    private Long inventory;

    private Long goodsClassifyId;

}
