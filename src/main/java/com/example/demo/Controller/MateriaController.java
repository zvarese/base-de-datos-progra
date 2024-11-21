package com.example.demo.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //method implementations with walkthroughs below

    @PutMapping
    Mono<MateriaEntity> createOrUpdateMovie(@RequestBody MateriaEntity newMovie) {
        return materiaRepository.save(newMovie);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MateriaEntity> getMovies() {
        return materiaRepository.findAll();
    }
}
