package com.creseliana.generator.service.util;

import java.util.Random;

public class NumberGenerator {
    private static final int MULTIPLIER = 3;

    public static int generateNumber(int amount) {
        return (new Random().nextInt(calculateLimit(amount))) + 1;
    }

    private static int calculateLimit(int amount) {
        return (amount + 1) * MULTIPLIER;
    }
}
