package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.exceptions.JSONSerilizationException;
import com.posada.santiago.alphapostsandcomments.business.gateways.DomainEventRepository;
import com.posada.santiago.alphapostsandcomments.business.gateways.EventBus;
import com.posada.santiago.alphapostsandcomments.domain.Post;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.CommentId;
import com.posada.santiago.alphapostsandcomments.domain.values.Content;
import com.posada.santiago.alphapostsandcomments.domain.values.PostId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Component
public class AddCommentEventUseCase implements Function<Mono<PostCreated>, Flux<DomainEvent>> {

    private DomainEventRepository repository;
    private EventBus bus;

    public AddCommentEventUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<PostCreated> postCreatedMono) {
        return postCreatedMono.flatMapIterable(event -> {
                        Post post = Post.from(PostId.of(event.aggregateRootId()), List.of(event));
                        post.addAComment(CommentId.of(event.getCommentId()),
                                new Author(event.getCommentAuthor()),
                                new Content(event.getComment()));
                        return post.getUncommittedChanges();
                    }).flatMap(domainEvent -> {
                    try {
                        return repository.saveEvent(domainEvent);
                    } catch (JSONSerilizationException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(domainEvent -> {
                    try {
                        bus.publish(domainEvent);
                    } catch (JSONSerilizationException e) {
                        throw new RuntimeException(e);
                    }
                    return domainEvent;
                });
    }
}
