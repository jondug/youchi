package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 店铺分类(StoreClassify)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:47
 */

@Data
@TableName("`store_classify`")
public class StoreClassify  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private String name;
          
    private Long pId;
    



}

