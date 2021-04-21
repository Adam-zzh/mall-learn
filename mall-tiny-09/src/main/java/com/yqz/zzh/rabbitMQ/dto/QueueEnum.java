package com.yqz.zzh.rabbitMQ.dto;

import lombok.Getter;

/*
 * @Description 消息队列枚举配置
 * Created by zzh on 2020/11/10.
 */
@Getter
public enum QueueEnum {

    /*
     * @Description 消息通知队列
     */
    QUENE_ORDER_CANCEL("mall.order.direct","mall.order.cancel","mall.order.cancel"),

    /*
     * @Description 消息通知ttl队列
     */
    QUENE_TTL_ORDER_CANCEL("mall.order.direct.ttl","mall.order.cancel.ttl","mall.order.cancel.ttl");

    /*
     * @Description 交换机名称
     */
    private String exchange;

    /*
     * @Description 队列名称
     */
    private String name;

    /*
     * @Description 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey){
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

 }
