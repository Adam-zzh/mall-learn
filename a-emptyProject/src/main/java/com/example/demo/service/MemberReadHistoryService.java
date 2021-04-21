package com.example.demo.service;

import com.example.demo.nosql.document.MemberReadHistory;

import java.util.List;

/**
 * @author zzh
 * @Description 会员用户浏览商品管理
 * @date 2020-11-08 16:05
 */
public interface MemberReadHistoryService {

    /*
     * @Description 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /*
     * @Description 批量删除浏览记录
     */
    int delete(List<String> ids);

    /*
     * @Description 获取浏览历史记录
     */
    List<MemberReadHistory> list(Long memberId);
}
