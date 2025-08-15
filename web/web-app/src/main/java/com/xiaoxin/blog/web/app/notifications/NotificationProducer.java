package com.xiaoxin.blog.web.app.notifications;


import com.xiaoxin.blog.web.app.config.RabbitNotificationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {
    private final RabbitTemplate rabbitTemplate;

    public void send(NotificationMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitNotificationConfig.EXCHANGE,
                RabbitNotificationConfig.ROUTING_KEY,
                message
        );
    }
}