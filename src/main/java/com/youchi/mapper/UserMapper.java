package com.youchi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from `user` where token = #{token}")
    User findToken(String token);

    @Select("SELECT * " +
            "FROM `user`\n" +
            "limit #{size},#{num}")
    List<User> page(int size, int num);

    @Select("select count(*) as total from user ")
    long count();

    @Select("select * from user where id = #{id} limit 1")
    User findById(Long id);

    @Select("select * from `user` "+
            "where `account` = #{account} and passwd = #{passwd} limit 1" )
    User selectUser(String account, String passwd);


}
