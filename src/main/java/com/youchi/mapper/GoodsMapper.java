package com.youchi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.model.Goods;
import com.youchi.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    @Select("select * from goods where id = #{id} limit 1")
    Goods findGoodsById(Long id);

    @Select("SELECT * " +
            "FROM `goods`\n" +
            "where `name` like #{keyword}"+
            "limit #{size},#{num}")
    List<Goods> page(int size, int num,String keyword);

    @Select("select count(*) as total from goods ")
    long count();

}
