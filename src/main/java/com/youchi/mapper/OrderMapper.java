package com.youchi.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import com.youchi.model.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2021-09-23 18:32:04
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select count(*) from `order`")
    int count();
    
     @Select("SELECT * FROM `order` limit #{size},#{num}")
    List<Order> page(int size, int num);
    
     @Select("select * from `order` where id = #{id}")
    Order findById(long id);

    @Select("SELECT o.id,o.user_id,o.total_money,g.goods_id,g.num,g.price FROM `order` AS o " +
            "JOIN order_goods AS g ON o.id=g.order_id " +
            "WHERE o.id=#{id}")
    List<OrderDto> findOrderByOrderId(Long id);
}
