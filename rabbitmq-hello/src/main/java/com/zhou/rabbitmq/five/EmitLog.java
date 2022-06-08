package com.zhou.rabbitmq.five;

import com.rabbitmq.client.Channel;
import com.zhou.rabbitmq.utils.RabbitMqUtils;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author zyh
 * @create 2022-05-31 17:13
 */
public class EmitLog {

    //交换机的名称
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME, "",null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出的消息:" + message);
        }
    }
}
