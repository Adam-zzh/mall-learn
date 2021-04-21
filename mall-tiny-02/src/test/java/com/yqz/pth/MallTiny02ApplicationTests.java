package com.yqz.pth;

import com.github.pagehelper.PageInfo;
import com.yqz.pth.mbg.model.PmsBrand;
import com.yqz.pth.service.PmsBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallTiny02ApplicationTests {

    @Autowired
    PmsBrandService pmsBrandService;
    @Test
    void contextLoads() {
        PageInfo<PmsBrand> listByPaginition = pmsBrandService.getListByPaginition(2, 8);
        System.out.println(listByPaginition);
    }

}
