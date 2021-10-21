package com.youchi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.model.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

    @Select("select * from `merchant` "+
            "where `account` = #{account} and passwd = #{passwd}")
    Merchant selectMerchant(String account, String passwd);

    @Select("select * from `merchant` where token = #{token}")
    Merchant findToken(String token);
}
