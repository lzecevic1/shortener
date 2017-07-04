package com.company.util;

import java.util.Random;

public class RandomStringGenerator {
    private static String ALPHA_NUMS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        while (stringBuilder.length() < 8) {
            int index = (int) (rnd.nextFloat() * ALPHA_NUMS.length());
            stringBuilder.append(ALPHA_NUMS.charAt(index));
        }
        return stringBuilder.toString();
    }
}
