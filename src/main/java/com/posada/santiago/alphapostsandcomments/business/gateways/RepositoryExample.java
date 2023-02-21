package com.posada.santiago.alphapostsandcomments.business.gateways;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.domain.Post;
import reactor.core.publisher.Flux;

import java.util.List;

public interface RepositoryExample {

    DomainEvent saveEventNoReactivo(DomainEvent event);
    List<DomainEvent> findByIdNoReactivo(String agregateRootId);

    void deleteById(String id);

    Flux<Post> findAll();

}
