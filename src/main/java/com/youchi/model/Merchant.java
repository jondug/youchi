package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 商家(Merchant)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:47
 */

@Data
@TableName("`merchant`")
public class Merchant  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private String nickname;
          
    private String account;
          
    private String passwd;
          
    private String token;
          
    private String phone;
       //关联的店铺数量    
    private Long storeNum;
    



}

