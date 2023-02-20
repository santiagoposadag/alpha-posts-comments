package com.posada.santiago.alphapostsandcomments.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.alphapostsandcomments.domain.values.Author;
import com.posada.santiago.alphapostsandcomments.domain.values.CommentId;
import com.posada.santiago.alphapostsandcomments.domain.values.Content;
import com.posada.santiago.alphapostsandcomments.domain.values.Title;

public class PostCreated extends DomainEvent {
    private final Title title;
    private final Author author;

    private final CommentId commentId;
    private final Content comment;

    private final Author commentAuthor;

    public PostCreated(Title title, Author author, CommentId commentId, Content comment, Author commentAuthor) {
        super("posada.santiago.postcreated");
        this.title = title;
        this.author = author;
        this.commentId = commentId;
        this.comment = comment;
        this.commentAuthor = commentAuthor;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public Content getComment() {
        return comment;
    }

    public Author getCommentAuthor() {
        return commentAuthor;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
