package com.posada.santiago.alphapostsandcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.CommentId;
import com.posada.santiago.alphapostsandcomments.domain.values.Content;

public class CommentAdded extends DomainEvent {

    private final CommentId id;
    private final Author author;
    private final Content content;


    public CommentAdded(CommentId id, Author author, Content content) {
        super("posada.santiago.commentcreated");
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentId getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }
}
