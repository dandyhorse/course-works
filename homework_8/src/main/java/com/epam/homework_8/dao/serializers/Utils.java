package com.epam.homework_8.dao.serializers;

import java.io.BufferedReader;
import java.io.StringReader;

public class Utils {

    public static String deleteLastBracket(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int i = stringBuilder.lastIndexOf("}");
        stringBuilder.deleteCharAt(i);
        return stringBuilder.toString();
    }

    public static BufferedReader stringToBuffer(String s) {
        return new BufferedReader(new StringReader(s));
    }

}
