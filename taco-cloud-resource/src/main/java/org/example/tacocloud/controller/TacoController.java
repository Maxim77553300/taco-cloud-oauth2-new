package org.example.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.example.tacocloud.domain.Taco;
import org.example.tacocloud.repository.TacoRepository;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//commented class for testing spring-data-rest!!!!!!!!!!!!!!!!!!!!!!!!!
//@RestController
//@RequestMapping(path = "/api/tacos",
//        produces = "application/json")
//@CrossOrigin(origins = {"http://tacocloud:8080", "http://tacocloud.com"})
//@RequiredArgsConstructor
public class TacoController {

//    private final TacoRepository tacoRepository;
//
//    @GetMapping(params = "recent")
//    public Iterable<Taco> recentTacos() {
//        PageRequest page = PageRequest.of(
//                0, 12, Sort.by("createdAt").descending());
//        return tacoRepository.findAll(page).getContent();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
//        Optional<Taco> optTaco = tacoRepository.findById(id);
//        if (optTaco.isPresent()) {
//            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping(consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Taco postTaco(@RequestBody Taco taco) {
//        return tacoRepository.save(taco);
//    }
}
