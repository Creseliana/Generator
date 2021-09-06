package com.creseliana.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class BaseGeneratorService implements GeneratorService {
    @Override
    public int generateNumber(int limit) {
        return new Random().nextInt(limit);
    }
}
