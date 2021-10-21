package com.youchi.controller;


import com.youchi.dto.GoodsClassifyDto;
import com.youchi.mapper.MerchantMapper;
import com.youchi.mapper.StoreMapper;
import com.youchi.model.Merchant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.youchi.mapper.GoodsClassifyMapper;
import com.youchi.model.GoodsClassify;
import com.youchi.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺的商品分类(GoodsClassify)controller层
 *
 * @author makejava
 * @since 2021-10-13 16:01:57
 */

@Api(tags = "店铺的商品分类")
@RestController
@RequestMapping("goodsClassify")
public class GoodsClassifyController {
    /**
     * 服务对象
     */
    @Autowired
    private GoodsClassifyMapper goodsClassifyMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private StoreMapper storeMapper;

    @ApiOperation("查询分类")
    @GetMapping("allClassifyByToken")
    public ResponseMessage allClassifyByToken(@RequestHeader(required = false) String token,Long storeId) {
//        final Merchant mapperToken = merchantMapper.findToken(token);
        final List<GoodsClassifyDto> byStoreId = goodsClassifyMapper.findByStoreId(storeId);
        return ResponseMessage.success(byStoreId);
    }


    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation("分页查询所有数据")
    @GetMapping("all/{size}/{num}")
    public ResponseMessage NLselectAll(@PathVariable(value = "size") int size,
                                     @PathVariable(value = "num") int num,
                                     @RequestHeader(required = false) String token
    ) {
        final List<GoodsClassify> page1 = goodsClassifyMapper.page((num - 1) * size, size);
        Map<String, Object> map = new HashMap<>();
        map.put("records", page1);
        map.put("total", goodsClassifyMapper.count());
        return ResponseMessage.success(map);
    }


    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("find/{id}")
    public ResponseMessage NLselectOne(@PathVariable(value = "id") Long id) {
        return ResponseMessage.success(goodsClassifyMapper.findById(id));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping("add")
    public ResponseMessage NLinsert(@RequestBody GoodsClassify goodsClassify) {
        goodsClassifyMapper.insert(goodsClassify);
        return ResponseMessage.success("ok");
    }

    /**
     * 修改数据
     *
     * @return 修改结果
     */
    @ApiOperation("修改数据")
    @PostMapping("modify")
    public ResponseMessage NLupdate(@RequestBody GoodsClassify goodsClassify) {
        goodsClassifyMapper.updateById(goodsClassify);
        return ResponseMessage.error("ok");
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @ApiOperation("删除数据")
    @DeleteMapping("del/{id}")
    public ResponseMessage NLdelete(@PathVariable(value = "id") Long id) {
        goodsClassifyMapper.deleteById(id);
        return ResponseMessage.success("ok");
    }
}

