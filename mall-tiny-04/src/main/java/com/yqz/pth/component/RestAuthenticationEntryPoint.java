package com.yqz.pth.component;

import cn.hutool.json.JSONUtil;
import com.yqz.pth.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzh
 * @Description
 * @date 2020-09-07 20:29
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        if (e instanceof UsernameNotFoundException){
            response.getWriter().print(JSONUtil.toJsonStr(CommonResult.unauthorized(e.getMessage(),"用户名或密码错误")));
        }else {
            response.getWriter().print(JSONUtil.toJsonStr(CommonResult.unauthorized(e.getMessage())));
        }
        response.getWriter().flush();
    }
}
