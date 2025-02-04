package com.example.util;

public final class CapitalizingUtil {

    private CapitalizingUtil() {}

    // UPPER_CASE 문자열 -> 그중에서 A-Z, a-z, _ 구간 및 그 주변만 인식해서 Capitalizing 하는 메서드
    public static String capitalize(String str) {
        char[] origin = str.toCharArray();
        char[] target = new char[origin.length];

        target[0] = toUpperCase(origin[0]);
        for (int i = 1; i < origin.length; i++) {
            if (origin[i] == '_') {
                target[i] = ' ';
                continue;
            }

            if (origin[i - 1] == '_') {
                target[i] = toUpperCase(origin[i]);
            } else {
                target[i] = toLowerCase(origin[i]);
            }
        }

        return String.valueOf(target);
    }

    private static char toUpperCase(char ch) {
        return (char) (ch & ~0x20);
    }

    private static char toLowerCase(char ch) {
        return (char) (ch | ' ');
    }
}