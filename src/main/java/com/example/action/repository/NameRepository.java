package com.example.action.repository;

import com.example.action.model.Name;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface NameRepository extends ReactiveCrudRepository<Name, Long> {
}
