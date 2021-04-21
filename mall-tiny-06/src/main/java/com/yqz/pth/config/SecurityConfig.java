//package com.yqz.pth.config;
//
//import com.yqz.pth.component.JwtAuthenticationTokenFilter;
//import com.yqz.pth.component.RestAuthenticationEntryPoint;
//import com.yqz.pth.component.RestfulAccessDeniedHandler;
//import com.yqz.pth.dto.AdminUserDetails;
//import com.yqz.pth.mbg.model.UmsAdmin;
//import com.yqz.pth.mbg.model.UmsPermission;
//import com.yqz.pth.service.UmsAdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.List;
//
///**
// * @author zzh
// * @Description
// * @date 2020-09-07 20:20
// */
//@Configuration
//@EnableWebSecurity//Spring Security用于启用Web安全的注解
//@EnableGlobalMethodSecurity(prePostEnabled=true)//Spring Security默认是禁用注解的，要想开启注解， 需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解， 来判断用户对某个控制层的方法是否具有访问权限 
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UmsAdminService adminService;
//    @Autowired
//    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;//：当用户没有访问权限时的处理器，用于返回JSON格式的处理结果；
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;//当未登录或token失效时，返回JSON格式的结果
//
//    /*
//      *@Description 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器
//      **/
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf 跨域请求仿造
//                .disable()
//                .sessionManagement()// 基于token，所以不需要session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-resources/**",
//                        "/v2/api-docs/**"
//                )
//                .permitAll()
//                .antMatchers("/user/login", "/user/register")// 对登录注册要允许匿名访问
//                .permitAll()
//                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
//                .permitAll()
////                .antMatchers("/**")//测试时全部运行访问
////                .permitAll()
//                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
//                .authenticated();
//        // 禁用缓存
//            httpSecurity.headers().cacheControl();
//        // 添加JWT filter
//        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        //处理异常 添加自定义未授权和未登录结果返回
//        httpSecurity.exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
//                .authenticationEntryPoint(restAuthenticationEntryPoint);
//    }
//
//    /*
//      *@Description 用于配置UserDetailsService及PasswordEncoder；
//      **/
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }
//    /*
//      *@Description SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder；
//      **/
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /*
//      *@Description  SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现；
//      **/
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //获取登录用户信息
//        return username -> {
//            UmsAdmin admin = adminService.getAdminByUsername(username);
//            if (admin != null) {
//                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
//                return new AdminUserDetails(admin,permissionList);//SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限），需要自行实现；
//            }
//            throw new UsernameNotFoundException("用户名或密码错误");
//        };
//    }
//
//    /*
//      *@Description 在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
//      **/
//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
