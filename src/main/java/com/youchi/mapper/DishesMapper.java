package com.youchi.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.youchi.model.Dishes;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 菜品(Dishes)表服务接口
 *
 * @author makejava
 * @since 2021-09-23 19:17:16
 */
@Mapper
public interface DishesMapper extends BaseMapper<Dishes> {

    @Select("select count(*) from `dishes`")
    int count();
    
     @Select("SELECT * FROM `dishes` limit #{size},#{num}")
    List<Dishes> page(int size, int num);
    
     @Select("select * from `dishes` where id = #{id}")
    Dishes findById(long id);
    
}
