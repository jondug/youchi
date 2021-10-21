package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 菜品分类(DishesClassify)表实体类
 *
 * @author makejava
 * @since 2021-09-23 16:27:54
 */

@Data
@TableName("`DishesClassify`")
public class DishesClassify {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Long storeId;


}

