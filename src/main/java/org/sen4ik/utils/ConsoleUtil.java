package org.sen4ik.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleUtil {

    private static int getMaxLength(String... strings) {
        int len = Integer.MIN_VALUE;
        for (String str : strings) {
            len = Math.max(str.length(), len);
        }
        return len;
    }

    private static String padString(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        return sb.append(fill(' ', len - str.length())).toString();
    }

    private static String fill(char ch, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void printBox(String... strings) {
        int maxBoxWidth = getMaxLength(strings);
        String line = "+" + fill('-', maxBoxWidth + 2) + "+";
        System.out.println(line);
        for (String str : strings) {
            System.out.printf("| %s |%n", padString(str, maxBoxWidth));
        }
        System.out.println(line);
    }
}
