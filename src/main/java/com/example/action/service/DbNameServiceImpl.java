package com.example.action.service;

import com.example.action.model.Name;
import com.example.action.repository.NameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@ConditionalOnProperty(prefix = "service", name = "name", havingValue = "db")
public class DbNameServiceImpl implements NameService {

    private final NameRepository nameRepository;

    @Override
    public Mono<Name> getNameById(Long id) {
        return nameRepository.findById(id);
    }

    @Override
    public Mono<Name> createName(Mono<Name> nameMono) {
        return nameRepository.saveAll(nameMono).next();
    }
}
