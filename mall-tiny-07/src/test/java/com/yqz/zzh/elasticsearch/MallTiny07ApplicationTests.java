package com.yqz.zzh.elasticsearch;

import com.yqz.zzh.elasticsearch.service.EsProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallTiny07ApplicationTests {

    @Autowired
    private String productIndexName;
    @Autowired
    EsProductService esProductService;

    @Test
    void contextLoads() {
    }

}
