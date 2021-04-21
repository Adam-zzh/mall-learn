package com.yqz.pth.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zzh
 * @Description 订单超时取消并解锁库存的定时器
 * @date 2020-09-14 10:30
 */
@Component
@Slf4j
public class OrderTimeOutCancelTask {

    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder(){
        log.info("取消订单，并根据sku编号释放锁定库存");
        System.out.println("取消订单，并根据sku编号释放锁定库存");
    }

}

