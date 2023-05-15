package com.example.action.config;

import com.example.action.model.Name;
import jakarta.jms.Destination;
import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;

@Configuration
@ConfigurationProperties(prefix = "action.jms")
@Setter
@Getter
public class JmsConfig {

    private String nameQueue;
    private String clientQueue;

    @Bean
    public Destination nameQueue() {
        return new ActiveMQQueue(nameQueue);
    }

    @Bean
    public Destination clientQueue() {
        return new ActiveMQQueue(clientQueue);
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        var messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        var typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("name", Name.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }
}
