package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 菜品(Dishes)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:48
 */

@Data
@TableName("`dishes`")
public class Dishes  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private String name;
          
    private Float price;
          
    private Long storeId;
          
    private Long dishesClassifyId;
       //封面    
    private String cover;
       //库存    
    private Long inventory;
    



}

