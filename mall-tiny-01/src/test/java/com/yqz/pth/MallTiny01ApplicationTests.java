package com.yqz.pth;

import com.github.pagehelper.PageInfo;
import com.yqz.pth.mbg.model.PmsBrand;
import com.yqz.pth.service.PmsBrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
class MallTiny01ApplicationTests {
    @Autowired
    private PmsBrandService pmsBrandService;

    @Test
    void contextLoads() {
//        List<PmsBrand> list = pmsBrandService.getlist("华为", 50, 60);
//        System.out.println(list.size()+" "+Arrays.asList(list));
        PageInfo<PmsBrand> listByPaginition = pmsBrandService.getListByPaginition(1, 10);
        System.out.println(listByPaginition);
    }

}
