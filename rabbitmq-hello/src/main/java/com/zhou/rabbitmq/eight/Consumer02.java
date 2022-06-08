package com.zhou.rabbitmq.eight;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zhou.rabbitmq.utils.RabbitMqUtils;

/**
 * 死信队列 之 消费者 C1
 */

public class Consumer02 {

    //死信队列名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();


        System.out.println("等待接收消息...");


        DeliverCallback deliverCallback = (consumer,message) ->{
            System.out.println("Consumer02 接受的消息是: " + new String(message.getBody(),"UTF-8"));
        };

        channel.basicConsume(DEAD_QUEUE,true,deliverCallback,consumerTag -> { });

    }
}
