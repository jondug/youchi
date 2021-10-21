package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Store)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:18:27
 */

@Data
@TableName("`store`")
public class Store  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private String name;
       //地址    
    private String address;
       //店铺分类    
    private Long storeClassifyId;
       //商家id 负责人    
    private Long merchantId;
       //注册时间    
    private String registrationTime;
       //简介    
    private String intro;
       //店铺图片    
    private String photo;

    //固定电话
    private String fixedTel;

    public Store(String name, String address, Long storeClassifyId, Long merchantId, String intro, String photo) {
        this.name = name;
        this.address = address;
        this.storeClassifyId = storeClassifyId;
        this.merchantId = merchantId;
        this.intro = intro;
        this.photo = photo;
    }
    public Store(){}
}

