package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.exceptions.JSONSerilizationException;
import com.posada.santiago.alphapostsandcomments.business.gateways.DomainEventRepository;
import com.posada.santiago.alphapostsandcomments.business.gateways.EventBus;
import com.posada.santiago.alphapostsandcomments.business.generic.UseCaseForCommand;
import com.posada.santiago.alphapostsandcomments.domain.Post;
import com.posada.santiago.alphapostsandcomments.domain.commands.CreatePostCommand;
import com.posada.santiago.alphapostsandcomments.domain.values.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreatePostUseCase extends UseCaseForCommand<CreatePostCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public CreatePostUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreatePostCommand> createPostCommandMono) {
        return createPostCommandMono.flatMapIterable(command -> {
            Post post = new Post(PostId.of(command.getPostId()),
                    new Title(command.getTitle()),
                    new Author(command.getPostId()),
                    CommentId.of(command.getCommentId()),
                    new Content(command.getComment()),
                    new Author(command.getCommentAuthor()));
            return post.getUncommittedChanges();
        }).flatMap(event -> {
                    try {
                        return repository.saveEvent(event);
                    } catch (JSONSerilizationException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(event -> {
                    try {
                        bus.publish(event);
                    } catch (JSONSerilizationException e) {
                        throw new RuntimeException(e);
                    }
                    return event;
        });
    }
}
