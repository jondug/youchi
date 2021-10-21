package com.youchi.controller;

import com.youchi.mapper.DishesClassifyMapper;
import com.youchi.model.DishesClassify;
import com.youchi.responseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "菜品分类")
@Controller
@ResponseBody
@RequestMapping("dishesClassify")
public class DishesClassifyController {

    @Autowired
    DishesClassifyMapper dishesClassifyMapper;


    @GetMapping("allClassify/{size}/{num}/{storeId}")
    public ResponseMessage allClassify(@PathVariable(value = "size") int size,
                                       @PathVariable(value = "num") int num ,
                                       @PathVariable(value = "storeId") int storeId ,
                                       String keyWord){
        if (keyWord != null) {
            final List<DishesClassify> page1 = dishesClassifyMapper.page((num - 1) * size, size,"%"+keyWord+"%",storeId);
            Map<String, Object> map = new HashMap<>();
            map.put("records", page1);
            map.put("total", dishesClassifyMapper.count(storeId));
            return ResponseMessage.success(map);
        }
        final List<DishesClassify> page1 = dishesClassifyMapper.page((num - 1) * size, size,"%"+"%",storeId);
        Map<String, Object> map = new HashMap<>();
        map.put("records", page1);
        map.put("total", dishesClassifyMapper.count(storeId));
        return ResponseMessage.success(map);
    }

    @PostMapping("addClassify/{storeId}")
    public ResponseMessage addClassify( String name,@PathVariable(value = "storeId") Long storeId){
        final DishesClassify dishesClassify = new DishesClassify();
        dishesClassify.setName(name);
        dishesClassify.setStoreId(storeId);
        dishesClassifyMapper.insert(dishesClassify);
        return ResponseMessage.success("添加成功");
    }


    @DeleteMapping("delClassify/{id}")
    public ResponseMessage delClassify(@PathVariable("id") Long id){
        dishesClassifyMapper.deleteById(id);
        return ResponseMessage.success("删除成功");
    }

    @PostMapping("modifyClassify")
    public ResponseMessage modifyClassify(@RequestBody DishesClassify dishesClassify){
        dishesClassifyMapper.updateById(dishesClassify);
        return ResponseMessage.success("修改成功");
    }

}
