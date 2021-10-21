package com.youchi.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.dto.GoodsClassifyDto;
import org.apache.ibatis.annotations.Mapper;
import com.youchi.model.GoodsClassify;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 店铺的商品分类(GoodsClassify)表服务接口
 *
 * @author makejava
 * @since 2021-10-13 16:02:41
 */
@Mapper
public interface GoodsClassifyMapper extends BaseMapper<GoodsClassify> {

    @Select("select count(*) from `goods_classify`")
    int count();
    
     @Select("SELECT * FROM `goods_classify` limit #{size},#{num}")
    List<GoodsClassify> page(int size, int num);
    
     @Select("select * from `goods_classify` where id = #{id}")
    GoodsClassify findById(long id);

     @Select("SELECT g.id ,g.name,g.store_id ,s.name AS store_name from `goods_classify` AS g \n" +
             "LEFT JOIN `store` AS s ON  g.store_id=s.id WHERE g.store_id = #{id}")
    List<GoodsClassifyDto> findByStoreId(Long id);
}
