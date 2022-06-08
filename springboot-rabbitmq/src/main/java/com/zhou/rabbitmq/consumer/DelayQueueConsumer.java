package com.zhou.rabbitmq.consumer;

import com.zhou.rabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消费者
 *
 * 基于插件的延迟消息
 *
 * @author zyh
 * @create 2022-06-03 14:54
 */
@Slf4j
@Component
public class DelayQueueConsumer {

    //监听消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message){
        String msg = new String(message.getBody());

        log.info("当前时间:{},收到的延迟队列消息:{}",new Date().toString(),msg);
    }
}
