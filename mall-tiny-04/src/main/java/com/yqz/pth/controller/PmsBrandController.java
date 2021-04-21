package com.yqz.pth.controller;

import com.github.pagehelper.PageInfo;
import com.yqz.pth.common.api.CommonResult;
import com.yqz.pth.mbg.model.PmsBrand;
import com.yqz.pth.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-09-06 15:28
 */
@Controller
@Api(tags="PmsBrandController",description="商品品牌管理")
@RequestMapping("brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    /*
      *@Description 获取所有品牌列表
      *@author zzh
      *@Param
      *@return
      **/
    @PreAuthorize("hasAnyAuthority('pms:brand:read')")
    @ApiOperation("获取所有品牌列表")
    @GetMapping("brands")
    @ResponseBody
    public CommonResult<List<PmsBrand>> brands(){
        return CommonResult.success(pmsBrandService.getAllBranks());
    }

    @ApiOperation("添加品牌")
    @PostMapping("brand")
    @ResponseBody
    public CommonResult addBrand(PmsBrand brand){
        int i = pmsBrandService.addBrand(brand);
        if (i == 1){
            LOGGER.debug("add brand success{}",brand);
            return  CommonResult.success(brand,"添加成功");
        }else{
            LOGGER.debug("add brand fail{}",brand);
            return CommonResult.failed("添加失败");
        }
    }

    @ApiOperation("更新指定id品牌信息")
    @PutMapping("brand/{id}")
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id")Long id,PmsBrand brand){
        int i = pmsBrandService.updateById(id,brand);
        if (i == 1){
            LOGGER.debug("updata brand success{}",brand);
            return  CommonResult.success(brand,"更新成功");
        }else{
            LOGGER.debug("updata brand fail{}",brand);
            return CommonResult.failed("更新失败");
        }
    }

    @ApiOperation("删除指定id品牌信息")
    @DeleteMapping("brand/{id}")
    @ResponseBody
    public CommonResult delBrand(@PathVariable("id")Long id){
        int i = pmsBrandService.deleteBrandById(id);
        if (i == 1){
            LOGGER.debug("delete brand success, id = {}", id);
            return CommonResult.success(null,"删除成功");
        }else{
            LOGGER.debug("delete brand fail, id={}", id);
            return  CommonResult.failed("删除操作失败");
        }
    }

    @ApiOperation("分页查询品牌商品信息")
    @GetMapping("queryBrandsByPaginition")
    @ResponseBody
    public CommonResult<PageInfo<PmsBrand>> queryBrandsByPaginition(@RequestParam(value = "brand",required = false)PmsBrand brand,
                                                                    @RequestParam(value = "pagNo", defaultValue="1") Integer pageNo,
                                                                    @RequestParam(value = "pageSize", defaultValue="3") Integer pageSize){
        LOGGER.debug("分页查询品牌商品信息，检索条件：{},当前页：{}，每页大小：{}",brand,pageNo,pageSize);
        return  CommonResult.success(pmsBrandService.queryBrandsByPaginition(brand,pageNo,pageSize));
    }

    @ApiOperation("获取指定id的品牌信息")
    @GetMapping("brand/{id}")
    @ResponseBody
    public CommonResult<PmsBrand> getBrandById(@PathVariable("id")Long id){
        PmsBrand brand = pmsBrandService.getBrandById(id);
        LOGGER.debug("query brand By id success {}", brand);
        return CommonResult.success(brand,"获取成功");
    }
}
