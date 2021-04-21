package com.yqz.pth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzh
 * @Description
 * @date 2020-09-04 20:39
 */
@Configuration
@MapperScan({"com.yqz.pth.mbg.mapper","com.yqz.pth.mapper"})
public class MybatisConfig {
}
