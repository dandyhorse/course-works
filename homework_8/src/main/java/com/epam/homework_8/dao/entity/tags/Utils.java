package com.epam.homework_8.dao.entity.tags;

import com.epam.homework_8.dao.exceptions.EntityException;

import java.io.BufferedReader;
import java.io.StringReader;

public class Utils {

    public static String deleteLastBracket(String s) {
        int i = s.lastIndexOf("}");
        StringBuilder stringBuilder = new StringBuilder(s);
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

    public static String deleteAttributeFrom(String innerString, String attribute, String value) {
        return innerString.replaceAll(Utils.formatTag("%s : %s", attribute, value), "");
    }

    private static void validateBrackets(String string) {
        int test = string.lastIndexOf("}");
        String newString = string.substring(test, string.length()).trim();
        if (!newString.equals("}")) {
            throw new EntityException("invalid tag Artist{} in file");
        }
    }

    public static String formatTag(String format, String... tag) {
        return String.format(format, tag);
    }

    public static String readerToString(BufferedReader in) {
        StringBuilder text = new StringBuilder();
        in.lines().forEach(text::append);
        return text.toString();
    }

    public static BufferedReader stringToBufferReader(String s) {
        return new BufferedReader(new StringReader(s));
    }

}
