package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 优惠劵(Coupons)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:48
 */

@Data
@TableName("`coupons`")
public class Coupons  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private Integer type;
       //结算总价金额不得低于此价格    
    private Float totalMoney;
       //满减金额    
    private Long discountsMoney;
       //发布者    
    private Long publisherId;
          
    private String name;
       //开始时间    
    private String startTime;
       //结束时间    
    private String endTime;
    



}

