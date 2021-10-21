package com.youchi.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class AllStoreDto {
    private long id;
    @TableField("`name`")
    private String name;
    private String address;
    private String intro;
    private String storeClassifyName;
    private String registrationTime;
    private long storeClassifyId;
    private long merchantId;
    private String photo;

}
