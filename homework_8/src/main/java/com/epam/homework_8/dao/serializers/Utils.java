package com.epam.homework_8.dao.serializers;

import com.epam.homework_8.dao.exceptions.EntityException;

import java.io.BufferedReader;
import java.io.StringReader;

public class Utils {

    public static String deleteLastBracket(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int i = stringBuilder.lastIndexOf("}");
        stringBuilder.deleteCharAt(i);
        return stringBuilder.toString();
    }

    public static String deleteLastBracketInArtistTag(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int i = stringBuilder.lastIndexOf("}");
        stringBuilder.deleteCharAt(i);
        String string = stringBuilder.toString();
        validateBrackets(string);
        return string;
    }

    private static void validateBrackets(String string) {
        int test = string.lastIndexOf("}");
        String newString = string.substring(test, string.length()).trim();
        if (!newString.equals("}")) {
            throw new EntityException("invalid tag Artist{} in file");
        }
    }

    public static String getFormatTag(String format, String... tag) {
        return String.format(format, tag);
    }

    public static BufferedReader stringToBuffer(String s) {
        return new BufferedReader(new StringReader(s));
    }

}
