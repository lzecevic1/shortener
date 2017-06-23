package com.company.Helper;

import java.util.Random;

/**
 * Created by lzecevic on 6/22/17.
 */

public class RandomStringGenerator {

    // Metoda za generisanje random stringa duzine 8 znakova
    String generateString() {
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
