package com.posada.santiago.alphapostsandcomments.domain;

import co.com.sofka.domain.generic.EventChange;
import com.posada.santiago.alphapostsandcomments.domain.events.CommentAdded;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import com.posada.santiago.alphapostsandcomments.domain.values.CommentId;
import com.posada.santiago.alphapostsandcomments.domain.values.PostId;

import java.util.ArrayList;

public class PostChange extends EventChange {

    public PostChange(Post post){

        apply((PostCreated event)-> {
            post.title = event.getTitle();
            post.author = event.getAuthor();
            post.comments = new ArrayList<>();
        });

        apply((CommentAdded event)-> {
            Comment comment = new Comment(event.getId(), event.getAuthor(), event.getContent());
            post.comments.add(comment);
        });

    }
}
