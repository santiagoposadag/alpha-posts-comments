package com.posada.santiago.alphapostsandcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;

public class PostCreated extends DomainEvent {
    private final Title title;

    public PostCreated(Title title) {
        super("posada.santiago.postcreated");
        this.title = title;
    }

    public Title getTitle() {
        return title;
    }
}
