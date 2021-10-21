package com.youchi.controller;


import com.youchi.mapper.AdminMapper;
import com.youchi.mapper.CouponsMapper;
import com.youchi.mapper.MerchantMapper;
import com.youchi.model.Coupons;
import com.youchi.responseMessage.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Coupons)表控制层
 *
 * @author makejava
 * @since 2021-09-22 11:16:42
 */
@Api(tags = "优惠劵")
@RestController
@RequestMapping("coupons")
public class CouponsController {
    /**
     * 服务对象
     */
    @Autowired
    private CouponsMapper couponsMapper;

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("all/{size}/{num}")
    public ResponseMessage selectAll(@PathVariable(value = "size") int size,
                                     @PathVariable(value = "num") int num,
                                     String keyword
    ) {
        final List<Coupons> page = couponsMapper.page((num - 1) * size, size, "%" + keyword + "%");
        System.out.println("%" + keyword + "%");
        Map<String, Object> map = new HashMap<>();
        map.put("records", page);
        map.put("total", couponsMapper.count());
        return ResponseMessage.success(map);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseMessage selectOne(@PathVariable Long id) {
        return ResponseMessage.success(couponsMapper.findById(id));
    }

    /**
     * 管理员新增数据
     *
     * @param coupons 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseMessage adminInsert(
            @RequestHeader(required = true) String token,
            @RequestBody Coupons coupons) {
        couponsMapper.insert(coupons);
        return ResponseMessage.success("ok");
    }

    /**
     * 管理员新增数据
     *
     * @param coupons 实体对象
     * @return 新增结果
     */
    @PostMapping("merchantAdd")
    public ResponseMessage merchantInsert(
            @RequestHeader(required = true) String token,
            @RequestBody Coupons coupons) {
        couponsMapper.insert(coupons);
        return ResponseMessage.success("ok");
    }

    /**
     * 修改数据
     *
     * @param coupons 实体对象
     * @return 修改结果
     */
    @PostMapping("modify")
    public ResponseMessage update(
            @RequestBody Coupons coupons) {
        couponsMapper.updateById(coupons);
        return ResponseMessage.success("ok");

    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping("del")
    public ResponseMessage delete(
            @PathVariable Long id) {
        couponsMapper.deleteById(id);
        return ResponseMessage.success("pk");
    }
}

