package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create() {
        System.out.println("CREATE");
        return null;
    }


    @GetMapping
    public ResponseEntity<TodoResponse> readOne() {
        System.out.println("READ ONE");
        return null;
    }

    @GetMapping
    public ResponseEntity<List<TodoEntity>> readAll() {
        System.out.println("READ ALL");
        return null;
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoEntity> update() {
        System.out.println("UPDATE");
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoEntity> deleteOne() {
        System.out.println("DELETE");
        return null;
    }
    @DeleteMapping
    public ResponseEntity<TodoEntity> deleteALL() {
        System.out.println("DELETE ALL");
        return null;
    }
}
