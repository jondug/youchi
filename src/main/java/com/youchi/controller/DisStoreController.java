package com.youchi.controller;






import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.youchi.mapper.DisStoreMapper;
import com.youchi.model.DisStore;
import com.youchi.responseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (DisStore)controller层
 *
 * @author makejava
 * @since 2021-09-24 09:38:32
 */

@Api(tags = "参加优惠的店铺")
@RestController
@RequestMapping("disStore")
public class DisStoreController  {
    /**
     * 服务对象
     */
    @Autowired
    private DisStoreMapper disStoreMapper;
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
     final List<DisStore> page1 = disStoreMapper.page((num - 1) * size, size);
        Map<String, Object> map = new HashMap<>();;
        map.put("records", page1);
        map.put("total", disStoreMapper.count());
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
        return ResponseMessage.success(disStoreMapper.findById(id));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
     @ApiOperation("新增数据")
    @PostMapping("add")
    public ResponseMessage insert(@RequestBody DisStore disStore) {
        disStoreMapper.insert(disStore);
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
    public ResponseMessage update(@RequestBody DisStore disStore) {
        disStoreMapper.updateById(disStore);
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
        disStoreMapper.deleteById(id);
        return ResponseMessage.success("ok");
    }
}

