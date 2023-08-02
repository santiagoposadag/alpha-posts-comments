package com.posada.santiago.alphapostsandcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Author implements ValueObject<String> {

    private String author;

    public Author(String author) {
        this.author = author;
    }

    public Author() {
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String value() {
        return author;
    }
}
