package com.yqz.zzh.rabbitMQ.component;

import com.yqz.zzh.rabbitMQ.dto.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zzh
 * @Description 取消订单消息的发送者
 * @date 2020-11-10 21:09
 */
@Component
@Slf4j
public class CancelOrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final long delayTimes){
        //给延迟消息队列发送消息 postProcessMessage返回的消息通常是原始消息的修改版本
        amqpTemplate.convertAndSend(QueueEnum.QUENE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUENE_TTL_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            }
        });

        log.info("send delay message. orderId:{}", orderId);
    }
}
