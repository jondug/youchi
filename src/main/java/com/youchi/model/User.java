package com.youchi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2021-09-22 11:06:52
 */
@SuppressWarnings("serial")

@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String account;

    private String passwd;

    private String nickname;

    private String sex;

    private String phone;

    private Float money;

    private String token;


    public User(String account, String passwd, String nickname, String sex, String phone) {
        this.account = account;
        this.passwd = passwd;
        this.nickname = nickname;
        this.sex = sex;
        this.phone = phone;
    }
    public User(){

    }




}

