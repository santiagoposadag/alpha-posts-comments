package com.posada.santiago.alphapostsandcomments.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Title implements ValueObject<String> {

    private String title;

    public Title(String title) {
        this.title = title;
    }

    public Title() {
    }

    @Override
    public String value() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
