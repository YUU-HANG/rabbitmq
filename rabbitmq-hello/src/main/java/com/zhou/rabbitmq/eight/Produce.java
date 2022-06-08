package com.zhou.rabbitmq.eight;

import com.rabbitmq.client.Channel;
import com.zhou.rabbitmq.utils.RabbitMqUtils;

import java.nio.charset.StandardCharsets;

/**
 * 死信队列 之 生产者代码
 *
 * @author zyh
 * @create 2022-06-02 17:17
 */
public class Produce {

    //普通交换机的名称
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //死信消息 设置 TTL 时间 (time to live)
//        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();

        for (int i = 1; i < 11; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,message.getBytes(StandardCharsets.UTF_8));

        }
    }
}
