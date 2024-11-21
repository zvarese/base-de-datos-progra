package com.example.demo.Repository;

import com.example.demo.Model.MateriaEntity;
import com.example.demo.Model.PersonEntity;

import java.util.Optional;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Mono;

public interface MateriaRepository extends ReactiveNeo4jRepository<MateriaEntity, String> {
    Mono<MateriaEntity> findOneByTitle(String name);

    @Query("MATCH (m:Materia)-[r]-(p:Person) WHERE m.title = $title RETURN m, collect(r), collect(p)")
    Optional<MateriaEntity> findWithRelationsByTitle(@Param("title") String title);



}