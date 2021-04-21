package com.yqz.zzh.rabbitMQ.controller;

import com.yqz.zzh.rabbitMQ.common.api.CommonResult;
import com.yqz.zzh.rabbitMQ.dto.OrderParam;
import com.yqz.zzh.rabbitMQ.service.OmsPortalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzh
 * @Description
 * @date 2020-11-10 22:03
 */
@RestController
@Api("订单系统门户管理")
@RequestMapping("order")
public class OmsPortalController {
    @Autowired
    private OmsPortalService omsPortalService;

    @ApiOperation("生成订单")
    @PostMapping("/generateOrder")
    public CommonResult generateOrder(@RequestBody OrderParam orderParam){
        CommonResult commonResult = omsPortalService.generateOrder(orderParam);
        return commonResult;
    }
}
