package com.zhou.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyh
 * @create 2022-06-03 13:57
 */
@Configuration
public class DelayedQueueConfig {

    //交换机
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    //队列
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    //RoutingKey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    //声明交换机 基于插件的
    @Bean
    public CustomExchange delayedExchange() {

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");

        //交换机名称 类型 是否需要持久化 是否需要自动删除 其他的参数
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, arguments);
    }

    //声明队列
    @Bean
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    //绑定
    @Bean
    public Binding delayedQueueBindingDelayedExchange(
            @Qualifier("delayedQueue") Queue delayedQueue,
            @Qualifier("delayedExchange") CustomExchange delayedExchange) {

        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();

    }
}
