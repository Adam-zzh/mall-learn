package com.yqz.zzh.rabbitMQ.config;

import com.yqz.zzh.rabbitMQ.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzh
 * @Description 消息队列配置
 * @date 2020-11-10 20:18
 */
@Configuration
public class RabbitMqConfig {

    /*
     * @Description 订单消息实际消费队列所绑定的交换机
     * @Param
     * @Return org.springframework.amqp.core.DirectExchange
     * Created by zzh on 2020/11/10.
     */
    @Bean
    DirectExchange orderDirect(){
        return ExchangeBuilder.directExchange(QueueEnum.QUENE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /*
     * @Description 订单延迟队列所绑定的交换机
     * @Param
     * @Return org.springframework.amqp.core.DirectExchange
     * Created by zzh on 2020/11/10.
     */
    @Bean
    DirectExchange orderTtlDirect(){
        return  ExchangeBuilder.directExchange(QueueEnum.QUENE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /*
     * @Description 订单实际消费队列
     * @Param
     * @Return org.springframework.amqp.core.Queue
     * Created by zzh on 2020/11/10.
     */
    @Bean
    Queue orderQueue(){
        return new Queue(QueueEnum.QUENE_ORDER_CANCEL.getName());
    }

    /*
     * @Description 订单延迟队列（死信队列）
     * @Param
     * @Return org.springframework.amqp.core.Queue
     * Created by zzh on 2020/11/10.
     */
    @Bean
    Queue orderTtlQueue(){
        return QueueBuilder.durable(QueueEnum.QUENE_TTL_ORDER_CANCEL.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUENE_ORDER_CANCEL.getExchange())
                .withArgument("x-dead-letter-routing-key",QueueEnum.QUENE_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /*
     * @Description 将订单队列绑定到交换机
     * @Param orderDirect 实际消费队列所绑定的交换机
     * @Param orderQueue 实际消费队列
     * @Return org.springframework.amqp.core.Binding
     * Created by zzh on 2020/11/10.
     */
    @Bean
    Binding orderBinding(DirectExchange orderDirect, Queue orderQueue){
        return BindingBuilder.bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUENE_ORDER_CANCEL.getRouteKey());
    }

    /*
     * @Description 将订单延迟队列绑定到交换机
     * @Param orderTtlDirect 延迟队列所绑定的交换机
     * @Param orderTtlQueue 死信队列
     * @Return org.springframework.amqp.core.Binding
     * Created by zzh on 2020/11/10.
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlDirect, Queue orderTtlQueue){
        return BindingBuilder.bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUENE_TTL_ORDER_CANCEL.getRouteKey());
    }
}
