package com.example.action.controller;

import com.example.action.messaging.JmsMessagingService;
import com.example.action.model.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/name")
public class NameController {

    private final JmsMessagingService messagingService;

    @GetMapping()
    public Name getName() {
        return messagingService.receiveName();
    }

    @PostMapping(consumes = "application/json")
    public void postName(@RequestBody Name name) {
        messagingService.sendName(name, this::addNameSource);
    }

    private Message addNameSource(Message message) throws JMSException {
        message.setStringProperty("X_NAME_SOURCE", "WEB");
        return message;
    }
}
