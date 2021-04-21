package com.yqz.pth.controller;

import com.yqz.pth.common.api.CommonResult;
import com.yqz.pth.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zzh
 * @Description 根据电话号码获取验证码的接口和校验验证码的接口
 * @date 2020-09-06 21:21
 */
@Api(tags = "UmsMemberController",description = "会员注册登录管理")
@Controller
@RequestMapping("sso")
public class UmsMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("获取短信验证码")
    @GetMapping("getAuthCode")
    @ResponseBody
    public CommonResult getAuthCode(String telPhone){
        return memberService.generateAuthCode(telPhone);
    }

    @ApiOperation("校验短信验证码")
    @PostMapping("verifyAuthCode")
    @ResponseBody
    public CommonResult verifyAuthCode(String telPhone, String authCode){
        return memberService.verifyAuthCode(telPhone,authCode);
    }
}
