package com.yqz.zzh.rabbitMQ.dto;

import lombok.Data;

/**
 * @author zzh
 * @Description
 * @date 2020-11-10 21:58
 */
@Data
public class OrderParam {

    //收货地址Id
    private long receiveAddressId;
    //优惠券Id
    private long couponId;
    //使用积分
    private Integer useIntegration;
    //支付方式
    private Integer payType;
}
