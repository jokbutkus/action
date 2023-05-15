package com.example.action.service;

import com.example.action.messaging.JmsMessagingService;
import com.example.action.model.Name;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "jms")
public class JmsNameServiceImpl implements NameService {

    private final JmsMessagingService messagingService;

    @Override
    public Mono<Name> getNameById(Long id) {
        return Mono.justOrEmpty(messagingService.receiveName());
    }

    @Override
    public Mono<Name> createName(Mono<Name> nameMono) {
        var name = nameMono.block();
        messagingService.sendName(name, this::addNameSource);
        return Mono.justOrEmpty(name);
    }

    private Message addNameSource(Message message) throws JMSException {
        message.setStringProperty("X_NAME_SOURCE", "WEB");
        return message;
    }
}
