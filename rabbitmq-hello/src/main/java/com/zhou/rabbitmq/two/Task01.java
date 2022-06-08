package com.zhou.rabbitmq.two;

import com.rabbitmq.client.Channel;
import com.zhou.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author zyh
 * @create 2022-05-28 22:26
 */
public class Task01 {

    //队列名称
    public static final String QUEUE_NAME = "hello";

    //接收消息
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //队列的声明
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //从控制台当中接受信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            /**
             * 发送一个消息
             * 1. 发送到哪个交换机
             * 2. 路由的 Key 值是哪个 本次是队列的名称
             * 3. 其他参数信息
             * 4. 发送消息的消息体
             */
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("发送消息完成:" + message);
        }

    }
}
