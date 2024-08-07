package ru.babim.template.helloworld.hellomicroservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.babim.template.helloworld.hellomicroservice.api.HelloApi;
import ru.babim.template.helloworld.hellomicroservice.dto.name.NameDto;

@RestController
public class HelloController implements HelloApi {

    @Override
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello World!");
    }

    @Override
    public ResponseEntity<?> hello(String name) {
        return ResponseEntity.ok("Hello, %s!".formatted(name));
    }

    @Override
    public ResponseEntity<?> hello(NameDto name) {
        return ResponseEntity.ok("Hello, %s %s!".formatted(name.firstName(), name.lastName()));
    }
}
