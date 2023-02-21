package com.posada.santiago.alphapostsandcomments.application.adapters.bus;

import com.posada.santiago.alphapostsandcomments.application.generic.serializer.JSONMapperImpl;

import java.time.Instant;

public class Notification {
    private final String type;
    private final String body;
    private final Instant instant;

    public Notification(String type, String body) {
        this.type = type;
        this.body = body;
        this.instant = Instant.now();
    }
    private Notification(){
        this(null,null);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "type='" + type + '\'' +
                ", body='" + body + '\'' +
                ", instant=" + instant +
                '}';
    }

    public String getBody() {
        return body;
    }

    public Instant getInstant() {
        return instant;
    }

    public Notification deserialize(String aSerialization) {
        return (Notification) new JSONMapperImpl().readFromJson(aSerialization, Notification.class);
    }

    public String serialize() {
        return new JSONMapperImpl().writeToJson(this);
    }

    public static Notification from(String aNotification){
        return new Notification().deserialize(aNotification);
    }
}
