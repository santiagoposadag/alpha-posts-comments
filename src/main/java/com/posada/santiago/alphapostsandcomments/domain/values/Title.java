package com.posada.santiago.alphapostsandcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Title implements ValueObject<String> {

    private String title;

    public Title( String title) {
        if(title.length() <= 9){
            throw new IllegalArgumentException();
        }
        this.title = Objects.requireNonNull(title);
    }

    @Override
    public String value() {
        return title;
    }
}
