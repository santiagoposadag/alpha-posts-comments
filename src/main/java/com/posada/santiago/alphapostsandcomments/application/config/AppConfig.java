package com.posada.santiago.alphapostsandcomments.application.config;


import com.posada.santiago.alphapostsandcomments.application.generic.serializer.JSONMapper;
import com.posada.santiago.alphapostsandcomments.application.generic.serializer.JSONMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JSONMapper getJsonMapper(){
        return new JSONMapperImpl();
    }
}
