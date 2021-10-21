package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 店铺的商品分类(GoodsClassify)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:47
 */

@Data
@TableName("`goods_classify`")
public class GoodsClassify  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private String name;

    private Long storeId;
    



}

