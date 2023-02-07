package com.example.action.controller;

import com.example.action.mail.MailService;
import com.example.action.messaging.JmsMessagingService;
import com.example.action.model.Name;
import com.example.action.service.NameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.jms.JMSException;
import javax.jms.Message;
import java.text.MessageFormat;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/name")
public class NameController {

    private final NameService nameService;
    private static final String MAIL_FORMAT = """
            {0} {1} was posted.
            Have a good day.""";

    private final MailService mailService;

    @GetMapping("/{id}")
    public Mono<Name> getNameById(@PathVariable("id") Long id) {
        return nameService.getNameById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Name> createName(@RequestBody Name name) {
        return nameService.createName(Mono.just(name));
    }

    @PostMapping("/something")
    public Mono<String> postSomething(@RequestBody Mono<String> body) {
        return body;
    }
}
