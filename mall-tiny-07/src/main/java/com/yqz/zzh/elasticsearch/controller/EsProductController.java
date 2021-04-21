package com.yqz.zzh.elasticsearch.controller;

import cn.hutool.json.JSONUtil;
import com.yqz.zzh.elasticsearch.common.api.CommonPage;
import com.yqz.zzh.elasticsearch.common.api.CommonResult;
import com.yqz.zzh.elasticsearch.nosql.document.EsProduct;
import com.yqz.zzh.elasticsearch.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-10-01 22:56
 */
@Controller
@RequestMapping("esProduct")
@Api("商品搜索功能")
@Slf4j
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @ApiOperation("生成所有数据库商品的ES")
    @PostMapping("importAll")
    @ResponseBody
    public CommonResult importAll(){
        int i = esProductService.importAll();
        log.info("一共导入%d条记录到ES中",i);
        return  CommonResult.success("一共导入"+i+"条记录到ES中");
    }

    @ApiOperation("删除es某一条索引")
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public CommonResult delete(@PathVariable("id")Long id){
        esProductService.delete(id);
        log.info("删除ES中id为%d索引",id);
        return  CommonResult.success(null);
    }

    @ApiOperation("创建一条指定的数据库记录到es中")
    @PostMapping("create/{id}")
    @ResponseBody
    public CommonResult create(@PathVariable("id")Long id){
        EsProduct esProduct = esProductService.create(id);
        log.info("创建一条记录到es中，{}",esProduct);
        if (esProduct!=null){
            return  CommonResult.success("创建一条记录到es中,"+ JSONUtil.toJsonStr(esProduct));
        }else {
            return  CommonResult.failed("");
        }
    }

    @ApiOperation("批量删除es某些条索引")
    @DeleteMapping("delete/batch")
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        esProductService.delete(ids);
        log.info("删除ES中id为{}的索引",ids);
        return  CommonResult.success(null);
    }

    @ApiOperation("简单搜索，产品名称、副标题、关键字")
    @GetMapping("search")
    @ResponseBody
    public CommonResult search(String keyWord,
                               @RequestParam(value = "pageNum",defaultValue = "0") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        Page<EsProduct> search = esProductService.search(keyWord, pageNum, pageSize);
        log.info("简单关键词搜索，搜索范围产品名称、副标题、关键字，搜索内容：{}",keyWord);
        return CommonResult.success(CommonPage.restPage(search));
    }
}
