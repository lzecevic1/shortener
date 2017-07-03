package com.company.Util;

import java.util.Random;

public class RandomStringGenerator {

    public String generateString() {
        String alphaNums = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphaNums.length());
            salt.append(alphaNums.charAt(index));
        }
        return salt.toString();
    }
}
