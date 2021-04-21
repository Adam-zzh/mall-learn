package com.example.demo.controller;

import com.example.demo.common.api.CommonResult;
import com.example.demo.nosql.document.MemberReadHistory;
import com.example.demo.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-11-08 16:20
 */
@Api("会员浏览历史商品记录")
@RestController
@RequestMapping("memReadHistory")
@Slf4j
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("生成历史商品记录")
    @PostMapping("createSPRecord")
    public CommonResult createSPRecord(@RequestBody MemberReadHistory memberReadHistory){
        if (memberReadHistory.getMemberId() == null || memberReadHistory.getProductId() == null){
            return  CommonResult.failed("请求参数错误.");
        }
        int i = memberReadHistoryService.create(memberReadHistory);
        log.info("用户{}访问了一次{}，并生成了{}条记录", memberReadHistory.getMemberNickName(),memberReadHistory.getProductName(),i);
        return CommonResult.success(null, "生成了"+i+"条记录");
    }

    @ApiOperation("批量删除浏览历史记录")
    @PostMapping("delete")
    public CommonResult delete(@RequestBody List<String> ids){
        int delete = memberReadHistoryService.delete(ids);
        log.info("删除了"+delete+"条历史记录");
        return CommonResult.success(null, "删除了"+delete+"条历史记录");
    }

    @ApiOperation("展示浏览记录")
    @GetMapping("list")
    public CommonResult list(@RequestParam("memberId") Long memberId){
        List<MemberReadHistory> list = memberReadHistoryService.list(memberId);
        return CommonResult.success(list, "ok");
    }

}
