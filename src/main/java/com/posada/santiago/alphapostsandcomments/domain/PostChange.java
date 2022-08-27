package com.posada.santiago.alphapostsandcomments.domain;

import co.com.sofka.domain.generic.EventChange;
import com.posada.santiago.alphapostsandcomments.domain.events.PostCreated;
import com.posada.santiago.alphapostsandcomments.domain.values.PostId;

public class PostChange extends EventChange {

    public PostChange(Post post){
        apply((PostCreated event)-> {
            post.title = event.getTitle();
            post.comments =
        });
    }
}
