package com.zhou.rabbitmq.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zhou.rabbitmq.utils.RabbitMqUtils;

/**
 * 这是一个工作线程 (相当于之前的消费者)
 * @author zyh
 * @create 2022-05-28 21:42
 */
public class Worker01 {
    //队列名称
    public static final String QUEUE_NAME = "hello";

    //接收消息
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //消息的接收
        DeliverCallback deliverCallback = (consumerTag,message) -> {
            System.out.println("接收到的消息:" + new String(message.getBody()));
        };

        //消息接收被取消时 执行下面的内容
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消息者取消消息接口回调逻辑");
        };

        System.out.println("C2 等待接收消息....");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
