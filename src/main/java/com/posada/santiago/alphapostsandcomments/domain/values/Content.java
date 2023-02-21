package com.posada.santiago.alphapostsandcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Content implements ValueObject<String> {

    private String content;


    public Content(String content) {
        this.content = content;
    }

    @Override
    public String value() {
        return content;
    }
}
