package com.example.demo.Repository;
import com.example.demo.Model.MateriaEntity;
import com.example.demo.Model.PersonEntity;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface MateriaRepository extends ReactiveNeo4jRepository<MateriaEntity, String>{
    Mono<MateriaEntity> findOneByTitle(String name);
}