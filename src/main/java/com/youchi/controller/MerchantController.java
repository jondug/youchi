package com.youchi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youchi.mapper.MerchantMapper;
import com.youchi.model.Admin;
import com.youchi.model.Merchant;
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

@Api(tags = "商家")
@Controller
@RequestMapping("merchant")
@ResponseBody
public class MerchantController {
    @Autowired
    MerchantMapper merchantMapper;

    @ApiOperation("商家登录")
    @PostMapping("login")
    public ResponseMessage login(@ApiParam("登录信息")  @RequestBody LoginVo loginVo) {
        final Merchant merchant = merchantMapper.selectMerchant(loginVo.getAccount(), loginVo.getPasswd());
        if (merchant == null) {
            return ResponseMessage.error("登录失败");
        }
        final String s = UUID.randomUUID().toString();
        merchant.setToken(s);
        merchantMapper.updateById(merchant);
        return ResponseMessage.success(s);

    }
    @ApiOperation("商家信息")
    @PostMapping("merchantInfo")
    public ResponseMessage login(@ApiParam("token") @RequestHeader(required = false) String token) {
        final Merchant merchantInfo = merchantMapper.findToken(token);
        if(merchantInfo==null){
            return ResponseMessage.error("token不正确");
        }
        return ResponseMessage.success(merchantInfo);

    }



    @ApiOperation("商家注册")
    @PostMapping("register")
    public ResponseMessage register(@RequestBody @ApiParam("注册信息") RegisterVo registerVo) {
        if ("".equals(registerVo.getAccount()) || "".equals(registerVo.getPasswd())) {
            return ResponseMessage.error("信息填写不完整");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", registerVo.getAccount());
        final Merchant account = merchantMapper.selectOne(queryWrapper);
        if (account != null) {
            return ResponseMessage.error("账号存在");
        }
        String regex = "^[a-zA-Z]+$";
        String num = "^[0-9]+$";
        if(!Pattern.matches(num,registerVo.getPhone())||registerVo.getPhone().length()!=11){
            return ResponseMessage.error("手机格式不正确");
        }
        if (registerVo.getPasswd().length() < 6 ||
                Pattern.matches(regex, registerVo.getPasswd()) ||
                Pattern.matches(num, registerVo.getPasswd())
        ) {
            return ResponseMessage.error("密码长度要大于6并且不能是纯数字或纯字母");
        }
       Merchant merchant = new Merchant();
        merchant.setAccount(registerVo.getAccount());
        merchant.setPasswd(registerVo.getPasswd());
        merchant.setNickname(registerVo.getNickname());
        merchant.setPhone(registerVo.getPhone());
        merchantMapper.insert(merchant);
        return ResponseMessage.success("注册成功");

    }
}
