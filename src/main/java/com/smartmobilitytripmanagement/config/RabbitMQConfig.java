package com.smartmobilitytripmanagement.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "trip.exchange";
    public static final String COMPLETED_QUEUE = "trip.completed.queue";
    public static final String PRICED_QUEUE = "trip.priced.queue";
    public static final String COMPLETED_ROUTING_KEY = "trip.completed";
    public static final String TRIP_PRICED_ROUTING_KEY = "trip.priced";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue completedQueue() {
        return new Queue(COMPLETED_QUEUE);
    }

    @Bean
    public Queue pricedQueue() {
        return new Queue(PRICED_QUEUE);
    }

    @Bean
    public Binding bindingCompleted(Queue completedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(completedQueue).to(exchange).with(COMPLETED_ROUTING_KEY);
    }

    @Bean
    public Binding bindingPriced(Queue pricedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pricedQueue).to(exchange).with(TRIP_PRICED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();
        // Ignore __TypeId__ header, infer type from method signature logic
        converter.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.INFERRED);
        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
