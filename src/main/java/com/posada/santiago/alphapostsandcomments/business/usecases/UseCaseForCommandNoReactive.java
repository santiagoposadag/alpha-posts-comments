package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommandNoReactive <R extends Command> {
    List<DomainEvent> apply(R command);
}
