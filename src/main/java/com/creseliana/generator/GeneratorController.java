package com.creseliana.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gen")
public class GeneratorController {
    private final GeneratorService generatorService;

    @GetMapping
    public ResponseEntity<Integer> generateNumber(@RequestParam int limit) {
        int number = generatorService.generateNumber(limit);
        return ResponseEntity.ok(number);
    }
}
