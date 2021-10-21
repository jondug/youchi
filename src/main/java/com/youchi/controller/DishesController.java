package com.youchi.controller;






import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.youchi.mapper.DishesMapper;
import com.youchi.model.Dishes;
import com.youchi.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品(Dishes)controller层
 *
 * @author makejava
 * @since 2021-09-23 19:17:16
 */

@Api(tags = "菜品")
@RestController
@RequestMapping("dishes")
public class DishesController  {
    /**
     * 服务对象
     */
    @Autowired
    private DishesMapper dishesMapper;
   /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
     @ApiOperation("分页查询所有数据")
    @GetMapping("all/{size}/{num}")
    public ResponseMessage selectAll(@PathVariable(value = "size") int size,
                                     @PathVariable(value = "num") int num,
                                     @RequestHeader(required = false) String token
    ) {
     final List<Dishes> page1 = dishesMapper.page((num - 1) * size, size);
        Map<String, Object> map = new HashMap<>();;
        map.put("records", page1);
        map.put("total", dishesMapper.count());
        return ResponseMessage.success(map);
    }


 
    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("find/{id}")
    public ResponseMessage selectOne(@PathVariable(value = "id") Long id) {
        return ResponseMessage.success(dishesMapper.findById(id));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
     @ApiOperation("新增数据")
    @PostMapping("add")
    public ResponseMessage insert(@RequestBody Dishes dishes) {
        dishesMapper.insert(dishes);
        return ResponseMessage.success("ok");
    }

    /**
     * 修改数据
     *
     * 
     * @return 修改结果
     */
     @ApiOperation("修改数据")
    @PostMapping("modify")
    public ResponseMessage update(@RequestBody Dishes dishes) {
        dishesMapper.updateById(dishes);
        return ResponseMessage.error("ok");
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @ApiOperation("删除数据")
    @DeleteMapping("del/{id}")
    public ResponseMessage delete(@PathVariable(value = "id") Long id) {
        dishesMapper.deleteById(id);
        return ResponseMessage.success("ok");
    }
}

