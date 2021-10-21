package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Goods)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:47
 */

@Data
@TableName("`goods`")
public class Goods  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private String name;
       //商店id    
    private Long storeId;
       //分类id    
    private Long goodsClassifyId;
       //库存    
    private Long inventory;
       //价格    
    private Float price;
       //封面    
    private String cover;

    private Long merchantId;
    



}

