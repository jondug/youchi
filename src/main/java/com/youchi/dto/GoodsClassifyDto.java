package com.youchi.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class GoodsClassifyDto {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;


    private String name;

    private Long storeId;

    private String storeName;
}
