package com.yqz.pth.controller;

import com.yqz.pth.common.api.CommonResult;
import com.yqz.pth.common.utils.JwtTokenUtil;
import com.yqz.pth.mbg.model.UmsAdmin;
import com.yqz.pth.service.UmsAdminRoleRelationService;
import com.yqz.pth.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzh
 * @Description
 * @date 2020-09-08 21:33
 */
@Api(description = "用户操作接口",tags = "userController")
@Controller
@RequestMapping("user")
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsAdminRoleRelationService userRoleService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户注册")
    @PostMapping("register")
    @ResponseBody
    public CommonResult register(@RequestBody UmsAdmin umsAdmin, BindingResult result){
        UmsAdmin user = umsAdminService.getAdminByUsername(umsAdmin.getUsername());
        if (user!=null){
            return new CommonResult().failed("该用户已被注册");
        }
        UmsAdmin user1 = umsAdminService.register(umsAdmin);
        return new CommonResult().success(user1, "注册成功");
    }

    @ApiOperation("登录功能")
    @PostMapping("login")
    @ResponseBody
    public CommonResult login(String userName, String password, HttpServletRequest request) {
        String token = null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (passwordEncoder.matches(password, userDetails.getPassword())){
            token = jwtTokenUtil.generateToken(userDetails);
            LOGGER.debug("{}：登陆成功", userName);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return  new CommonResult().success(token,"登陆成功");
        }
        LOGGER.debug("{}：用户名密码错误，登陆失败", userName);
        return  new CommonResult().failed(userName+"，你的用户名密码错误");
    }

    @ApiOperation("获得用户所有权限")
    @GetMapping("permission/{userId}")
    @ResponseBody
    public CommonResult permission(@PathVariable Long userId){
        return  new CommonResult().success(userRoleService.getAllPermission(userId), null);
    }

}
