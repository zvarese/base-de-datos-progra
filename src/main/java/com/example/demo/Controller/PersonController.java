package com.example.demo.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.MateriaEntity;
import com.example.demo.Model.PersonEntity;
import com.example.demo.Repository.MateriaRepository;
import com.example.demo.Repository.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/personas")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    //method implementations with walkthroughs below

    @PutMapping
    Mono<PersonEntity> createOrUpdatePerson(@RequestBody PersonEntity newPerson) {
        return personRepository.save(newPerson);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<PersonEntity> getPersons() {
        return personRepository.findAll();
    }
}
