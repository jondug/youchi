package com.youchi.model;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 管理员(Admin)表实体类
 *
 * @author makejava
 * @since 2021-10-13 18:16:48
 */

@Data
@TableName("`admin`")
public class Admin  {

          @TableId(value = "id",type = IdType.AUTO)
        private Long id;
  
      
    private String nickname;
          
    private String account;
          
    private String passwd;
          
    private String token;
    



}

