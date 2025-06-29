package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(path="/demo")
public class DemoController {

    @GetMapping("/{id}")
    public Optional<Integer> getDemoId(@PathVariable int id){
        return Optional.of(id);
    }
}