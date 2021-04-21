package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzh
 * @Description
 * @date 2020-09-04 20:39
 */
@Configuration
@MapperScan({"com.yqz.zzh.elasticsearch.mbg.mapper","com.yqz.zzh.elasticsearch.mapper"})
public class MybatisConfig {
}
