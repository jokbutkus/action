package com.example.action.messaging;

import com.example.action.config.JmsConfig;
import com.example.action.model.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JmsMessagingService {

    private final JmsTemplate jmsTemplate;
    private final JmsConfig jmsConfig;

    public void sendName(Name name, MessagePostProcessor processor) {
        jmsTemplate.convertAndSend(jmsConfig.getNameQueue(), name, processor);
    }
}
