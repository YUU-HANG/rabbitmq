package com.zhou.rabbitmq.five;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.zhou.rabbitmq.utils.RabbitMqUtils;

/**
 * 消息接收
 *
 * @author zyh
 * @create 2022-05-31 16:50
 */
public class ReceiveLogs02 {

    //交换机的名称
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //声明一个队列 临时队列
        /**
         * 生成一个临时队列 队列的名称是随机的
         * 当消费者断开与队列的连接时, 队列就自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机与队列
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println("等待接收消息,把接收到的消息打印到屏幕上");

        //消息接收
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogs02控制台打印接收到的消息: " + new String(message.getBody(),"UTF-8"));
        };

        //消费者取消消息时回调接口

        channel.basicConsume(queueName,true,deliverCallback,consumerTag ->{ });

    }
}
