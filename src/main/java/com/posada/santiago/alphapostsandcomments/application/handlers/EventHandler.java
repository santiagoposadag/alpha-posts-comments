package com.posada.santiago.alphapostsandcomments.application.handlers;

import com.posada.santiago.alphapostsandcomments.application.adapters.bus.Notification;
import com.posada.santiago.alphapostsandcomments.application.config.RabbitConfig;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.JSONMapper;
import com.posada.santiago.alphapostsandcomments.business.usecases.AddCommentEventUseCase;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Service
public class EventHandler {

    private final JSONMapper mapper;
    private final AddCommentEventUseCase useCase;

    Logger logger = Logger.getLogger("events-handler");

    public EventHandler(JSONMapper mapper, AddCommentEventUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    @RabbitListener(queues = RabbitConfig.EVENTS_QUEUE)
    public void postCreatedHandler(String message) throws ClassNotFoundException {

        Notification notification = (Notification) mapper.readFromJson(message, Notification.class);
        logger.info(notification.toString());
        System.out.println(notification.getType());
        if(notification.getType().equals("com.posada.santiago.alphapostsandcomments.domain.events.PostCreated")){
            System.out.println("logger");
            useCase.apply(Mono.just((PostCreated) mapper.readFromJson(notification.getBody(),
                    Class.forName(notification.getType())))).subscribe();
        }else{
            System.out.println("logger no");
            logger.info("no usecase interested in " + notification.getType());
        }
    }
}
