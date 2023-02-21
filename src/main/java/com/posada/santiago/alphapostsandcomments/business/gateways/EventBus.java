package com.posada.santiago.alphapostsandcomments.business.gateways;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.exceptions.JSONSerilizationException;

public interface EventBus {
    void publish(DomainEvent event) throws JSONSerilizationException;

    void publishError(Throwable errorEvent) throws JSONSerilizationException;
}
