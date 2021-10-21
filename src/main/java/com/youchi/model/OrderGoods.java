package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (OrderGoods)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:18:45
 */

@Data
@TableName("`order_goods`")
public class OrderGoods  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private Long goodsId;
          
    private Float price;
          
    private Long num;
          
    private Long orderId;


    public OrderGoods(Long goodsId, Long num,Float price,Long orderId) {
        this.goodsId = goodsId;
        this.num = num;
        this.price = price;
        this.orderId = orderId;
    }

}

