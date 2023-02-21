package com.posada.santiago.alphapostsandcomments.application.generic.serializer.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JSONSerilizationException extends JsonProcessingException {
    public JSONSerilizationException(String msg) {
        super(msg);
    }
}
