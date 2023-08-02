package com.posada.santiago.alphapostsandcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;

public class PostCreated extends DomainEvent {
    private final String title;
    private final String author;

    public PostCreated(String title, String author) {
        super("posada.santiago.postcreated");
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
