package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.business.gateways.RepositoryExample;
import com.posada.santiago.alphapostsandcomments.domain.Post;
import com.posada.santiago.alphapostsandcomments.domain.commands.AddCommentCommand;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.CommentId;
import com.posada.santiago.alphapostsandcomments.domain.values.Content;
import com.posada.santiago.alphapostsandcomments.domain.values.PostId;

import java.util.List;
import java.util.stream.Collectors;

public class AddCommentNoReactive implements UseCaseForCommandNoReactive<AddCommentCommand>{

    private RepositoryExample repository;

    public AddCommentNoReactive(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AddCommentCommand command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getPostId());
        Post post = Post.from(PostId.of(command.getPostId()), events);
        post.addAComment(CommentId.of(command.getCommentId()),
                new Author(command.getAuthor()),
                new Content(command.getContent()));
        return post.getUncommittedChanges().stream().map(event -> {
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
