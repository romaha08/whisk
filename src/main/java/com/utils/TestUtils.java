package com.utils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TestUtils {
    private static Random r = new Random();

    public static Integer generateInt(int numDigits) {
        Random random = new Random();
        String str = String.valueOf(1 + random.nextInt(9));
        for (int i = 0; i < numDigits - 1; i++) {
            str = str.concat(String.valueOf(random.nextInt(10)));
        }
        return Integer.parseInt(str);
    }

    public static String getRandomString(int size) {
        byte[] array = new byte[size];
        r.nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
