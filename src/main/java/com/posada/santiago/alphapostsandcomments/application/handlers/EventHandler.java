package com.posada.santiago.alphapostsandcomments.application.handlers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {

    @RabbitListener(id = "Post-created")
    public void postCreatedHandler(String message){

    }
}
