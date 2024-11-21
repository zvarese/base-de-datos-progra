package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import com.example.demo.Model.PersonEntity;
import com.example.demo.Model.MateriaEntity;

public class Grafo {
    private Map<MateriaEntity, List<PersonEntity>> adjMateria;
    private Map<PersonEntity, List<MateriaEntity>> adjPersona;

    public Grafo() {}

    public Grafo(List<MateriaEntity> materias) {
        adjMateria = new HashMap<>();
        adjPersona = new HashMap<>();
        for (MateriaEntity materia : materias) {
            adjMateria.putIfAbsent(materia, new ArrayList<>(materia.getEstudiantes()));
            adjMateria.get(materia).addAll(materia.getEnseniadores());
            for (PersonEntity estudiante : materia.getEstudiantes()) {
                adjPersona.putIfAbsent(estudiante, new ArrayList<>());
                adjPersona.get(estudiante).add(materia);
            }
            for (PersonEntity enseniante : materia.getEnseniadores()) {
                adjPersona.putIfAbsent(enseniante, new ArrayList<>());
                adjPersona.get(enseniante).add(materia);
            }
        }
    }

    public void agregarArista(MateriaEntity materia, PersonEntity persona) {
        adjMateria.get(materia).add(persona);
        adjPersona.get(persona).add(materia);
    }

    public String BFS(MateriaEntity inicio) {
        StringBuilder resultado = new StringBuilder();
        Set<MateriaEntity> visitadoMateria = new HashSet<>();
        Set<PersonEntity> visitadoPersona = new HashSet<>();
        Queue<Object> cola = new LinkedList<>();
        visitadoMateria.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            Object actual = cola.poll();
            if (actual instanceof MateriaEntity) {
                MateriaEntity materia = (MateriaEntity) actual;

                resultado.append("Movie: ").append(materia.getTitle()).append("\n");
                for (PersonEntity persona : adjMateria.get(materia)) {
                    if (!visitadoPersona.contains(persona)) {
                        visitadoPersona.add(persona);
                        cola.add(persona);
                    }
                }
            } else if (actual instanceof PersonEntity) {
                PersonEntity persona = (PersonEntity) actual;
                System.out.println("Person: " + persona.getName());
                for (MateriaEntity materia : adjPersona.get(persona)) {
                    if (!visitadoMateria.contains(materia)) {
                        visitadoMateria.add(materia);
                        cola.add(materia);
                    }
                }
            }
        }
        return resultado.toString();
    }

    public String DFS(MateriaEntity inicio) {
        StringBuilder resultado = new StringBuilder();
        Set<MateriaEntity> visitadoMateria = new HashSet<>();
        Set<PersonEntity> visitadoPersona = new HashSet<>();
        dfsRecursivo(inicio, resultado, visitadoMateria, visitadoPersona);
        return resultado.toString();
    }

    private void dfsRecursivo(Object nodo, StringBuilder resultado,
            Set<MateriaEntity> visitadoMateria, Set<PersonEntity> visitadoPersona) {
        if (nodo instanceof MateriaEntity) {
            MateriaEntity materia = (MateriaEntity) nodo;
            if (visitadoMateria.contains(materia))
                return; 
            visitadoMateria.add(materia);
            resultado.append("Materia: ").append(materia.getTitle()).append("\n");

            for (PersonEntity persona : adjMateria.get(materia)) {
                dfsRecursivo(persona, resultado, visitadoMateria, visitadoPersona);
            }
        } else if (nodo instanceof PersonEntity) {
            PersonEntity persona = (PersonEntity) nodo;
            if (visitadoPersona.contains(persona))
                return;
            visitadoPersona.add(persona);
            resultado.append("Persona: ").append(persona.getName()).append("\n");

            for (MateriaEntity materia : adjPersona.get(persona)) {
                dfsRecursivo(materia, resultado, visitadoMateria, visitadoPersona);
            }
        }
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "adjMateria=" + adjMateria +
                ", adjPersona=" + adjPersona +
                '}';
    }

}