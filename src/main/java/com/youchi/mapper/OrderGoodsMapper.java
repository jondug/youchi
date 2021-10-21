package com.youchi.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.youchi.model.OrderGoods;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * (OrderGoods)表服务接口
 *
 * @author makejava
 * @since 2021-09-23 19:17:42
 */
@Mapper
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {

    @Select("select count(*) from `order_goods`")
    int count();
    
     @Select("SELECT * FROM `order_goods` limit #{size},#{num}")
    List<OrderGoods> page(int size, int num);
    
     @Select("select * from `order_goods` where id = #{id}")
    OrderGoods findById(long id);
    
}
