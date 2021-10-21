package com.youchi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.model.Coupons;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CouponsMapper extends BaseMapper<Coupons> {

    @Select("select * from coupons where id = #{id} limit 1")
    Coupons findById(Long id);

//    @Select("SELECT * " +
//            "FROM coupons\n" +
//            "limit #{size},#{num}")
    List<Coupons> page(int size, int num,String keyword);

    @Select("select count(*) as total from coupons ")
    long count();
}
