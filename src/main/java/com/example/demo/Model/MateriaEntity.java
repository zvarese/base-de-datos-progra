package com.example.demo.Model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("Materia")
public class MateriaEntity {
    @Id
    private final String title;
    
    @Property
    private final String tagline;

    @Relationship(type = "Estudia", direction = INCOMING)
    private Set<PersonEntity> estudiantes = new HashSet<>();

    @Relationship(type = "Ensenia", direction = INCOMING)
    private Set<PersonEntity> enseniador = new HashSet<>();

    public MateriaEntity(String tagline, String title) {
            this.tagline = tagline;
            this.title = title;
        }

    public String getTitle() {
        return title;
    }

    public String getTagline() {
        return tagline;
    }

    
}
