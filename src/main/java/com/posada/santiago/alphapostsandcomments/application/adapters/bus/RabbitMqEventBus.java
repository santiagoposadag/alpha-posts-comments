package com.posada.santiago.alphapostsandcomments.application.adapters.bus;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.application.config.RabbitConfig;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.JSONMapper;
import com.posada.santiago.alphapostsandcomments.business.gateways.EventBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqEventBus implements EventBus {
    private final RabbitTemplate rabbitTemplate;
    private final JSONMapper serializer;


    public RabbitMqEventBus(RabbitTemplate rabbitTemplate,  JSONMapper serializer) {
        this.serializer = serializer;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event){
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.writeToJson(event)
        );
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, notification.serialize().getBytes()
        );
    }

    @Override
    public void publishError(Throwable errorEvent){
        var event = new ErrorEvent(errorEvent.getClass().getTypeName(), errorEvent.getMessage());
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.writeToJson(event)
        );
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, notification.serialize().getBytes());
    }
}
