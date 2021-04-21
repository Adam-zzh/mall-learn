package com.yqz.zzh.mongodb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zzh
 * @Description
 * @date 2020-09-26 11:06
 */
@PropertySource("classpath:config.properties")
@Component
@Data
@ConfigurationProperties(prefix = "elasticsearch.product")
public class PropertiesConfig {

    private String indexName;

    @Bean
    public String productIndexName(){
        return this.indexName;
    }
}
