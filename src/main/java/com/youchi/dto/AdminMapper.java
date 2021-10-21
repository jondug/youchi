package com.youchi.dto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.youchi.model.Admin;

/**
 * 管理员(Admin)表服务接口
 *
 * @author makejava
 * @since 2021-09-22 17:40:45
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
