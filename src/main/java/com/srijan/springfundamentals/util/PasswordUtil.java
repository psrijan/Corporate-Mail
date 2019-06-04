package com.srijan.springfundamentals.util;

import java.util.Random;

public class PasswordUtil {


    public static String getRandomPassword() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }


}
