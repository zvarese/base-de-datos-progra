package com.example.demo.Repository;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

import com.example.demo.Model.PersonEntity;

import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveNeo4jRepository<PersonEntity, String>{
    Mono<PersonEntity> findOneByName(String name);
}