package com.example.util;

public final class CapitalizingUtil {

    private CapitalizingUtil() {}

    // UPPER_CASE 문자열 -> 그중에서 A-Z, a-z, _ 구간 및 그 주변만 인식해서 Capitalizing 하는 메서드
    public static String capitalize(String origin) {
        if (origin == null || origin.isBlank()) {
            return origin;
        }

        StringBuilder builder = new StringBuilder(origin.length());
        boolean isPreviousBlank = true;
        int i = 0;

        for (char ch = origin.charAt(i); isWhitespace(ch); ch = origin.charAt(i)) {
            i++;
            if (i >= origin.length()) {
                break;
            }
        }

        for (; i < origin.length(); i++) {
            char ch = origin.charAt(i);

            // underscores, hyphens to blank
            if (ch == '_' || ch == '-' || Character.isWhitespace(ch)) {
                // ignore the last blank part
                if (i + 1 >= origin.length()) {
                    continue;
                }
                char nextChar = origin.charAt(i + 1);
                if (nextChar != '_' && nextChar != '-' && !Character.isWhitespace(nextChar)) {
                    builder.append(' ');
                }

                isPreviousBlank = true;
                continue;
            }

            // Each first character of seperated words => to uppercase, others => to lowercase
            if ('A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z') { // priority: && > ||
                char convertedChar = isPreviousBlank ? toUpperCase(ch) : toLowerCase(ch);
                builder.append(convertedChar);
            }

            isPreviousBlank = false;
        }

        return builder.toString();
    }

    private static boolean isWhitespace(char ch) {
        return ch == '_' || ch == '-' || Character.isWhitespace(ch);
    }

    private static char toUpperCase(char ch) {
        return (char) (ch & ~' ');
    }

    private static char toLowerCase(char ch) {
        return (char) (ch | ' ');
    }
}