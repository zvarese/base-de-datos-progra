package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Grafo;
import com.example.demo.Model.MateriaEntity;
import com.example.demo.Repository.MateriaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/materias")
public class MateriaController {
    private final MateriaRepository materiaRepository;

    public MateriaController(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }
    // method implementations with walkthroughs below

    @PutMapping
    Mono<MateriaEntity> createOrUpdateMovie(@RequestBody MateriaEntity newMovie) {
        return materiaRepository.save(newMovie);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MateriaEntity> getMovies() {
        return materiaRepository.findAll();
    }

    @GetMapping("/{title}")
    public Mono<MateriaEntity> getMateriaWithRelations(@PathVariable String title) {
        return materiaRepository.findById(title)
                .switchIfEmpty(Mono.error(new RuntimeException("Materia no encontrada")));
    }

    @GetMapping("/grafo")
    public Mono<String> getGrafo() {
        return materiaRepository.findAll()
                .collectList() // Convertimos el Flux a un Mono<List<MovieEntity>>
                .map(materias -> {
                    Grafo grafo = new Grafo(materias); // Construimos el grafo con la lista de películas
                    return grafo.toString(); // Devolvemos la representación en String del grafo
                });
    }

    @GetMapping("/bfs")
    public Mono<String> getBFS() {
        return materiaRepository.findAll()
                .collectList() // Convertimos el Flux a un Mono<List<MovieEntity>>
                .map(materias -> {
                    Grafo grafo = new Grafo(materias); // Construimos el grafo con la lista de películas
                    return grafo.BFS(materias.get(0)); // Devolvemos la representación en String del grafo
                });
    }

    @GetMapping("/dfs")
    public Mono<String> getDFS() {
        return materiaRepository.findAll()
                .collectList() // Convertimos el Flux a un Mono<List<MovieEntity>>
                .map(materias -> {
                    Grafo grafo = new Grafo(materias); // Construimos el grafo con la lista de películas
                    return grafo.DFS(materias.get(0)); // Devolvemos la representación en String del grafo
                });
    }
}