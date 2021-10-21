package com.youchi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youchi.mapper.StoreClassifyMapper;
import com.youchi.model.StoreClassify;
import com.youchi.responseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商店分类")
@Controller
@ResponseBody
@RequestMapping("storeClassify")
public class StoreClassifyController {
    @Autowired
    StoreClassifyMapper storeClassifyMapper;



    @GetMapping("allClassify/{size}/{num}")
    public ResponseMessage allClassify(@PathVariable(value = "size") int size,
                                       @PathVariable(value = "num") int num ,
                                       String keyWord){

        Page page = new Page();
        page.setSize(size);
        page.setCurrent(num);
        if(keyWord==null){
            final Page page1 = storeClassifyMapper.selectPage(page, null);
            return ResponseMessage.success(page1);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name",keyWord);

        final Page page1 = storeClassifyMapper.selectPage(page, queryWrapper);
        return ResponseMessage.success(page1);
    }

    @PostMapping("addClassify/{name}")
    public ResponseMessage addClassify(@PathVariable("name") String name, Long pId){
        final StoreClassify storeClassify = new StoreClassify();
        storeClassify.setName(name);
        storeClassify.setPId(pId);
        storeClassifyMapper.insert(storeClassify);
        return ResponseMessage.success("添加成功");
    }


    @DeleteMapping("delClassify/{id}")
    public ResponseMessage delClassify(@PathVariable("id") Long id){
        storeClassifyMapper.deleteById(id);
        return ResponseMessage.success("删除成功");
    }

    @PostMapping("modifyClassify")
    public ResponseMessage modifyClassify(@RequestBody StoreClassify storeClassify){
        storeClassifyMapper.updateById(storeClassify);
        return ResponseMessage.success("修改成功");
    }



}
