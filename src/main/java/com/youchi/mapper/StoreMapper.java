package com.youchi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youchi.dto.AllStoreDto;
import com.youchi.model.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreMapper extends BaseMapper<Store> {

//    @Select("SELECT s.id,s.name ,s.address,c.name AS store_classify_name," +
//            "c.id AS storeClassifyId,s.merchant_id,s.registration_time,s.intro  " +
//            "FROM `store` AS s JOIN store_classify  AS c \n" +
//            "ON s.store_classify_id=c.id " +
//            "WHERE s.merchant_id=#{merchantId} and s.name LIKE #{keyword} " +
//            "order by s.id limit #{size},#{num}")
    List<AllStoreDto> page(int size, int num,String keyword,long merchantId);

//    List<AllStoreDto> page();
    @Select("select count(*) as total from store WHERE merchant_id=#{merchantId}")
    long count(long merchantId);

    @Select("select * from store where merchant_id = #{id}")
    List<Store> findStoreByMerchantId(long id);



}
