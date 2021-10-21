package com.youchi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youchi.mapper.AdminMapper;
import com.youchi.mapper.UserMapper;
import com.youchi.model.Admin;
import com.youchi.model.User;
//import com.youchi.model.service.UserService;
import com.youchi.responseMessage.ResponseMessage;
import com.youchi.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-09-22 11:16:43
 */
@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("all/{size}/{num}")
    public ResponseMessage selectAll(@PathVariable(value = "size") int size,
                                     @PathVariable(value = "num") int num,
                                     @RequestHeader(required = false) String token
    ) {

        final Admin token1 = adminMapper.findToken(token);
        if (token1 == null) {
            return ResponseMessage.error("请先登录");
        }
        final List<User> page1 = userMapper.page((num - 1) * size, size);
        Map<String, Object> map = new HashMap<>();
        map.put("records", page1);
        map.put("total", userMapper.count());
        return ResponseMessage.success(map);
    }


    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseMessage selectOne(@PathVariable(value = "id") Long id) {
        return ResponseMessage.success(userMapper.findById(id));
    }


    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PostMapping
    public ResponseMessage update(
            @RequestHeader(required = false) String token,
            @RequestBody User user) {
        final Admin token1 = adminMapper.findToken(token);
        if (token1 != null) {
            userMapper.updateById(user);
            return ResponseMessage.success("ok");
        }
        final User token2 = userMapper.findToken(token);
        if (token2 != null) {
            userMapper.updateById(user);
            return ResponseMessage.success("ok");
        }
        return ResponseMessage.error("not ok");
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping("del/{id}")
    public ResponseMessage delete(@PathVariable(value = "id") Long id, @RequestHeader(required = false) String token) {
        final Admin token1 = adminMapper.findToken(token);
        if (token1 == null) {
            return ResponseMessage.error("not find admin");
        }
        userMapper.deleteById(id);
        return ResponseMessage.success("ok");
    }


    @ApiOperation("用户登录")
    @PostMapping("login")
    public ResponseMessage login(@ApiParam("登录信息")  @RequestBody LoginVo loginVo) {
        final User user = userMapper.selectUser(loginVo.getAccount(), loginVo.getPasswd());
        if (user == null) {
            return ResponseMessage.error("登录失败");
        }
        final String s = UUID.randomUUID().toString();
        user.setToken(s);
        userMapper.updateById(user);
        return ResponseMessage.success(s);

    }
    @ApiOperation("用户信息")
    @PostMapping("userInfo")
    public ResponseMessage login(@ApiParam("token") @RequestHeader(required = false) String token) {
        final User userInfo = userMapper.findToken(token);
        if(userInfo==null){
            return ResponseMessage.error("token不正确");
        }
        return ResponseMessage.success(userInfo);

    }



    @ApiOperation("用户注册")
    @PostMapping("register")
    public ResponseMessage register(@RequestBody @ApiParam("注册信息") User registerVo) {
        if ("".equals(registerVo.getAccount()) || "".equals(registerVo.getPasswd())) {
            return ResponseMessage.error("信息填写不完整");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", registerVo.getAccount());
        final User account = userMapper.selectOne(queryWrapper);
        if (account != null) {
            return ResponseMessage.error("账号存在");
        }
        String regex = "^[a-zA-Z]+$";
        String num = "^[0-9]+$";
        if (registerVo.getPasswd().length() < 6 ||
                Pattern.matches(regex, registerVo.getPasswd()) ||
                Pattern.matches(num, registerVo.getPasswd())
        ) {
            return ResponseMessage.error("密码不能纯数字或纯字母");
        }
        final User user = new User(registerVo.getAccount(),registerVo.getPasswd(),registerVo.getNickname(),registerVo.getSex(),
                registerVo.getPhone());
        user.setMoney(0F);
        userMapper.insert(user);
        return ResponseMessage.success(1);

    }

}

