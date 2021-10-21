package com.youchi.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.youchi.model.DisStore;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * (DisStore)表服务接口
 *
 * @author makejava
 * @since 2021-09-24 09:38:32
 */
@Mapper
public interface DisStoreMapper extends BaseMapper<DisStore> {

    @Select("select count(*) from `dis_store`")
    int count();
    
     @Select("SELECT * FROM `dis_store` limit #{size},#{num}")
    List<DisStore> page(int size, int num);
    
     @Select("select * from `dis_store` where id = #{id}")
    DisStore findById(long id);
    
}
