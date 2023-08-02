package com.posada.santiago.alphapostsandcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Content implements ValueObject<String> {

    private String content;

    public Content(String content) {
        this.content = content;
    }

    public Content() {
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String value() {
        return content;
    }
}
