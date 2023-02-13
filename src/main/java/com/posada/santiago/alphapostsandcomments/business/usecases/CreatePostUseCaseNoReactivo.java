package com.posada.santiago.alphapostsandcomments.business.usecases;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.domain.Post;
import com.posada.santiago.alphapostsandcomments.domain.commands.CreatePostCommand;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.PostId;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;

import java.util.List;

public class CreatePostUseCaseNoReactivo implements UseCaseForCommandNoReactive{


    @Override
    public List<DomainEvent> apply(Command command) {
        CreatePostCommand createPost = (CreatePostCommand) command;
        Post post = new Post(PostId.of(createPost.getPostId()),
                new Title(createPost.getTitle()),
                new Author(createPost.getAuthor()));
        return post.getUncommittedChanges();
    }
}
