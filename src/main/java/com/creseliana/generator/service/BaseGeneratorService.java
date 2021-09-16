package com.creseliana.generator.service;

import com.creseliana.generator.api.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RequiredArgsConstructor
@Transactional
@Service
public class BaseGeneratorService implements GeneratorService {
    @Override
    public int generateNumber(int limit) {
        return new Random().nextInt(limit);
    }
}
