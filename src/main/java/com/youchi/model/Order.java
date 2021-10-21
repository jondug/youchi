package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Order)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:19:17
 */

@Data
@TableName("`order`")
public class Order  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private Long userId;
          
    private Float totalMoney;


    public Order(Long userId) {
        this.userId = userId;
    }

}

