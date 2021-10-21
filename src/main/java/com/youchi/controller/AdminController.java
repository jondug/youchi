package com.youchi.controller;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youchi.mapper.AdminMapper;
import com.youchi.model.Admin;
import com.youchi.responseMessage.ResponseMessage;
import com.youchi.vo.LoginVo;
import com.youchi.vo.RegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.regex.Pattern;

@Api(tags = "管理员")
@Controller
@RequestMapping("admin")
@ResponseBody
public class AdminController {
    @Autowired
    AdminMapper adminMapper;

    @GetMapping("test")
    public ResponseMessage test(){
        return ResponseMessage.success(adminMapper.selectList(null));
    }
    @ApiOperation("管理员登录")
    @PostMapping("login")
    public ResponseMessage login(@ApiParam("登录信息")  @RequestBody LoginVo loginVo) {
//        System.out.println(loginVo.getAccount());
        final Admin admin = adminMapper.selectAdmin(loginVo.getAccount(), loginVo.getPasswd());
        System.out.println(admin);
        if (admin == null) {
            return ResponseMessage.error("登录失败");
        }
        final String s = UUID.randomUUID().toString();
        admin.setToken(s);
        adminMapper.updateById(admin);
        return ResponseMessage.success(s);

    }
    @ApiOperation("管理员信息")
    @PostMapping("adminInfo")
    public ResponseMessage login(@ApiParam("token") @RequestHeader(required = false) String token) {
        final Admin adminInfo = adminMapper.findToken(token);
        if(adminInfo==null){
            return ResponseMessage.error("token不正确");
        }
        return ResponseMessage.success(adminInfo);

    }



    @ApiOperation("管理员注册")
    @PostMapping("register")
    public ResponseMessage register(@RequestBody @ApiParam("注册信息") RegisterVo registerVo) {
        if ("".equals(registerVo.getAccount()) || "".equals(registerVo.getPasswd())) {
            return ResponseMessage.error("信息填写不完整");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", registerVo.getAccount());
        final Admin account = adminMapper.selectOne(queryWrapper);
        if (account != null) {
            return ResponseMessage.error("账号存在");
        }
        String regex = "^[a-zA-Z]+$";
        String num = "^[0-9]+$";
        System.out.println("密码：" + registerVo.getPasswd());
        if (registerVo.getPasswd().length() < 6 ||
                Pattern.matches(regex, registerVo.getPasswd()) ||
                Pattern.matches(num, registerVo.getPasswd())
        ) {
            return ResponseMessage.error("密码不能纯数字或纯字母");
        }
        Admin admin = new Admin();
        admin.setAccount(registerVo.getAccount());
        admin.setPasswd(registerVo.getPasswd());
        adminMapper.insert(admin);
        return ResponseMessage.success(1);

    }



}
