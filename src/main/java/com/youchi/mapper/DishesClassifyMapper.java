package com.youchi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.dto.AllStoreDto;
import com.youchi.model.DishesClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishesClassifyMapper extends BaseMapper<DishesClassify> {
    @Select("SELECT *" +
            "FROM `dishes_classify` AS s  \n" +
            "WHERE s.store_id=#{storeId} and s.name LIKE #{key} " +
            "order by s.id limit #{size},#{num}")
    List<DishesClassify> page(int size, int num, String key, long storeId);

    @Select("select count(*) as total from dishes_classify WHERE store_id=#{storeId}")
    long count(long storeId);
}
