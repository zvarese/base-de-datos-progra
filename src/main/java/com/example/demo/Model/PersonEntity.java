package com.example.demo.Model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Persona")
public class PersonEntity {
    @Id
    private final String name;
    private final Integer born;

    public PersonEntity(Integer born, String name) {
        this.born = born;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getBorn() {
        return born;
    }
}