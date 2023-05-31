package com.example.action.service;

import com.example.action.mail.MailService;
import com.example.action.model.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Service
public class MailNameServiceImpl implements NameService {

    private static final String MAIL_FORMAT = """
            {0} {1} was posted.
            Have a good day.""";

    private final MailService mailService;

    @Override
    public Mono<Name> getNameById(final Long id) {
        return null;
    }

    @Override
    public Mono<Name> createName(final Mono<Name> nameMono) {
        var name = nameMono.block();
        mailService.sendMessage("test@email.com", "Name created",
              MessageFormat.format(MAIL_FORMAT, name.getFirstname(), name.getLastname()));
        return nameMono;
    }

}
