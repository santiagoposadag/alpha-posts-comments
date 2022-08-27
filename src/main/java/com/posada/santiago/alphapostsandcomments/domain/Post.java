package com.posada.santiago.alphapostsandcomments.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import com.posada.santiago.alphapostsandcomments.domain.values.PostId;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;

import java.util.List;

public class Post extends AggregateEvent<PostId> {

    protected Title title;

    protected List<Comment> comments;

    public Post(PostId entityId, Title title) {
        super(entityId);
        subscribe(new PostChange(this));
        appendChange(new PostCreated(title)).apply();
    }
}
