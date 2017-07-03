package com.company.util;

import java.util.Random;

public class RandomStringGenerator {
    private static String ALPHA_NUMS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public String generateString() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * ALPHA_NUMS.length());
            salt.append(ALPHA_NUMS.charAt(index));
        }
        return salt.toString();
    }
}
