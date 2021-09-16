package com.creseliana.generator.controller;

import com.creseliana.generator.api.service.GeneratorService;
import com.creseliana.generator.api.service.UserService;
import com.creseliana.generator.dto.UserRegisterRequest;
import com.creseliana.generator.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/gen")
public class GeneratorController {
    private final GeneratorService generatorService;
    private final UserService userService;

    @GetMapping("/num")
    public int generateNumber(@Positive @RequestParam int limit) {
        return generatorService.generateNumber(limit);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerUser(@Valid @RequestBody UserRegisterRequest newUser) {
        userService.registerUser(newUser);
    }

    @GetMapping
    public List<UserResponse> getLuckyUsers() {
        return userService.getLuckyUsers();
    }
}
