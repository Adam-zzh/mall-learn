package com.yqz.zzh.rabbitMQ.service.Iml;

import com.yqz.zzh.rabbitMQ.common.api.CommonResult;
import com.yqz.zzh.rabbitMQ.component.CancelOrderSender;
import com.yqz.zzh.rabbitMQ.dto.OrderParam;
import com.yqz.zzh.rabbitMQ.service.OmsPortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzh
 * @Description
 * @date 2020-11-10 21:31
 */
@Service
@Slf4j
public class OmsportalServiceImpl implements OmsPortalService {
    @Autowired
    private CancelOrderSender cancelOrderSender;


    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //todo 执行一系列下单操作
        log.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应在下单之后生成）
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null,"创建订单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {

        log.info("process cancel order. orderId:{}",orderId);
    }

    private void sendDelayMessageCancelOrder(long orderId) {
        long deplayTimes = 30*1000;//30s
        cancelOrderSender.sendMessage(orderId, deplayTimes);
    }
}
