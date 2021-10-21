package com.youchi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.model.Admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from `admin` "+
            "where `account` = #{account} and passwd = #{passwd} limit 1" )
    Admin selectAdmin(String account, String passwd);

    @Select("select * from `admin` where token = #{token}")
    Admin findToken(String token);
}
