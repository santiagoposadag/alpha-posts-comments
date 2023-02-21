package com.posada.santiago.alphapostsandcomments.application.generic.serializer.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JSONDeserializationException extends JsonProcessingException {
    public JSONDeserializationException(String msg) {
        super(msg);
    }
}
