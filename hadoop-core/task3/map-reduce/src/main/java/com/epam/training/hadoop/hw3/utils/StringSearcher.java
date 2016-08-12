package com.epam.training.hadoop.hw3.utils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Anton_Solovev
 * @since 8/12/2016.
 */
public class StringSearcher {
    private String line;
    private static final int CODE_OFFSET = 5;
    private static final String stringPattern = "((?:\\s[\\d]{3})\\s(?:[0-9])*)";

    public StringSearcher(String line) {
        this.line = line;
    }

    public String getBytes() {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(line);
        String bytes;
        if (matcher.find()) {
            String s = matcher.group();
            bytes = s.substring(CODE_OFFSET);
        } else {
            throw new RuntimeException("bytes is null. matching failure");
        }
        if (bytes.equals("")) {
            bytes = "0";
        }
        return bytes;
    }

    public String getIp() {
        StringTokenizer tokenizer = new StringTokenizer(line);
        return tokenizer.nextToken();
    }
}
