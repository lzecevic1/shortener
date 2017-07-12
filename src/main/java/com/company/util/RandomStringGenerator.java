package com.company.util;

import java.util.Random;

public class RandomStringGenerator {
    private static String ALPHA_NUMS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private Random rnd = new Random();

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < 8) {
            int index = (int) (rnd.nextFloat() * ALPHA_NUMS.length());
            stringBuilder.append(ALPHA_NUMS.charAt(index));
        }
        return stringBuilder.toString();
    }
}
