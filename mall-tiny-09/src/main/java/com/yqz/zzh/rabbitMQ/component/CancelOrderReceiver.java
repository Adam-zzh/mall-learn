package com.yqz.zzh.rabbitMQ.component;

import com.yqz.zzh.rabbitMQ.service.OmsPortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zzh
 * @Description 用于在取消订单的消息队列接收消息
 * @date 2020-11-10 21:23
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
@Slf4j
public class CancelOrderReceiver {
    @Autowired
    OmsPortalService omsPortalService;

    @RabbitHandler
    public void handle(Long orderId){
        log.info("recevice delay message orderId:{}",orderId);
        omsPortalService.cancelOrder(orderId);
    }

}
