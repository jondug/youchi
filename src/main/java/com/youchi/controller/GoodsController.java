package com.youchi.controller;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youchi.mapper.AdminMapper;
import com.youchi.mapper.GoodsMapper;
import com.youchi.mapper.MerchantMapper;
import com.youchi.model.Admin;
import com.youchi.model.Goods;

import com.youchi.model.Merchant;
import com.youchi.responseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "商品")
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsMapper goodsmapper;

    @Autowired
    private AdminMapper adminMapper;

@Autowired
private MerchantMapper merchantMapper;

    @GetMapping("allGoods")
    public ResponseMessage allGoods(@RequestHeader(required = false) String token){
        final Merchant token1 = merchantMapper.findToken(token);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("merchant_id", token1.getId());
        return ResponseMessage.success(goodsmapper.selectList(queryWrapper));
    }

    /**
     * 查找店铺是品牌
     *
     * @return
     */
    @GetMapping("all/{size}/{num}")
    public ResponseMessage NLfindGoodsByStore(
            @PathVariable(value = "size") int size,
            @PathVariable(value = "num") int num,
            @RequestHeader(required = false) String token,
            String keyWord
    ) {
//        final Admin token1 = adminMapper.findToken(token);
//        if(token1==null){
//            return ResponseMessage.error("请先登录");
//        }
        if (keyWord != null) {
            final List<Goods> page1 = goodsmapper.page((num - 1) * size, size, "%" + keyWord + "%");
            Map<String, Object> map = new HashMap<>();
            map.put("records", page1);
            map.put("total", goodsmapper.count());
            return ResponseMessage.success(map);
        }
        final List<Goods> page1 = goodsmapper.page((num - 1) * size, size, "%" + "%");
        Map<String, Object> map = new HashMap<>();
        map.put("records", page1);
        map.put("total", goodsmapper.count());
        return ResponseMessage.success(map);
    }

    /**
     * 添加商品
     *
     * @return
     */
    @PostMapping("add")
    public ResponseMessage addGoods(
            @RequestHeader(required = false) String token,
            @RequestBody Goods goods) {
        goodsmapper.insert(goods);
        return ResponseMessage.success("ok");
    }

    /**
     * 修改商品
     *
     * @return
     */
    @PostMapping("modify")
    public ResponseMessage modfiyGoods(
            @RequestHeader(required = false) String token,
            @RequestBody Goods goods) {
        goodsmapper.updateById(goods);
        return ResponseMessage.success("ok");
    }

    /**
     * 删除商品
     *
     * @return
     */
    @DeleteMapping({"del/{id}"})
    public ResponseMessage delGoods(@RequestHeader(required = false) String token,
                                    @PathVariable(value = "id") Long id) {
        goodsmapper.deleteById(id);
        return ResponseMessage.success("ok");
    }

    @ApiOperation("上传")
    @PostMapping("upload")
    public ResponseMessage upload(@ApiParam("文件") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "";
        System.out.println(file.getOriginalFilename());
        String split = file.getOriginalFilename();
        String substring = split.substring(split.lastIndexOf("."), split.length());
        if (substring.equals(".exe")) {
            return ResponseMessage.error("0");
        }
        String originalFilename = name + "" + substring;
        String lujing = "D:\\file\\youchi\\goods\\";
        File filePath = new File(lujing);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String url = lujing + originalFilename;
        File winFiledir = new File(url);
//        file.getBytes().length
        FileUtil.writeBytes(file.getBytes(), winFiledir);
        System.out.println("保存文件:" + winFiledir.getAbsolutePath());


        return ResponseMessage.success("youchi/goods/"+originalFilename);
    }
}
