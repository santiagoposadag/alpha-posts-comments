package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.business.gateways.RepositoryExample;
import com.posada.santiago.alphapostsandcomments.domain.Post;
import com.posada.santiago.alphapostsandcomments.domain.commands.CreatePostCommand;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.PostId;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class CreatePostUseCaseNoReactivo implements UseCaseForCommandNoReactive{

    /**
     * Mono y Flux
     * */

    private RepositoryExample repository;

    public CreatePostUseCaseNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }



    @Override
    public List<DomainEvent> apply(Command command) {
        CreatePostCommand createPost = (CreatePostCommand) command;
        Post post = new Post(PostId.of(createPost.getPostId()),
                new Title(createPost.getTitle()),
                new Author(createPost.getAuthor()));
        return post.getUncommittedChanges().stream().map(event -> {
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
