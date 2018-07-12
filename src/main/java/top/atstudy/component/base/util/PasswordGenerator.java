package top.atstudy.component.base.util;

import java.util.Random;

public class PasswordGenerator {

    private static char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z' };

    /**
     * @param length password length
     * @return password
     */
    public static String getPassword(int length) {
        if (length < 6 || length > 16) {
            throw new IllegalArgumentException("Password length is not illegal");
        }

        Random random = new Random();
        StringBuffer password = new StringBuffer();
        password.append(chars[random.nextInt(9)]);
        password.append(chars[random.nextInt(25) + 9]);
        password.append(chars[random.nextInt(25) + 34]);

        for (int i = 3; i < length; i++) {
            password.append(chars[random.nextInt(59)]);
        }

        return password.toString();
    }

}
