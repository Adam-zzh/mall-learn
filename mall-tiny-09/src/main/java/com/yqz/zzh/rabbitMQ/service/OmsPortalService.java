package com.yqz.zzh.rabbitMQ.service;

import com.yqz.zzh.rabbitMQ.common.api.CommonResult;
import com.yqz.zzh.rabbitMQ.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/*
 * @Description 订单管理系统前台Service
 * Created by zzh on 2020/11/10.
 */
public interface OmsPortalService {
    /*
     * @Description 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /*
     * @Description 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
