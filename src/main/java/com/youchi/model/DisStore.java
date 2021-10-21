package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 参加优惠的店铺(DisStore)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:48
 */

@Data
@TableName("`dis_store`")
public class DisStore  {


    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
          
    private Long storeId;
          
    private Long coupondId;
    



}

