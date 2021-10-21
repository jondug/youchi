package com.youchi.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long num;
    private Float price;
    private Float totalMoney;
}
