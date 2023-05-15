package com.example.action.service;

import com.example.action.model.Name;
import reactor.core.publisher.Mono;

public interface NameService {

    Mono<Name> getNameById(Long id);

    Mono<Name> createName(Mono<Name> nameMono);
}
