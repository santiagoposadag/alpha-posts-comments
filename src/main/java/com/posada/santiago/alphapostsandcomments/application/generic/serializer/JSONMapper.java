package com.posada.santiago.alphapostsandcomments.application.generic.serializer;

public interface JSONMapper {
    String writeToJson(Object object);
    Object readFromJson(String json, Class<?> clazz);
}
