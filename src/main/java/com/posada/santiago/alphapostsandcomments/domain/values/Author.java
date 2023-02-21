package com.posada.santiago.alphapostsandcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Author implements ValueObject<String> {

    private String author;

    public Author(String author) {
        this.author = author;
    }

    @Override
    public String value() {
        return author;
    }
}
