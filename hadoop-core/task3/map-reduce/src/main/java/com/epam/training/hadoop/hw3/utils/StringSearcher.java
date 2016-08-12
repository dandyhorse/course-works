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
    private static final String bytesPattern = "((?:\\s[\\d]{3})\\s(?:[0-9])*)";
    private static final int CODE_OFFSET = 5;
    private static final String browserPattern = "\"([A-z]+)?/";
    private static final int ODD_SIGN_OFFSET = 1;


    public StringSearcher(String line) {
        this.line = line;
    }

    public String getBytes() {
        Pattern pattern = Pattern.compile(bytesPattern);
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

    public String getBrowserName() {
        Pattern pattern = Pattern.compile(browserPattern);
        Matcher matcher = pattern.matcher(line);
        String name;
        if (matcher.find()) {
            String s = matcher.group();
            name = s.substring(ODD_SIGN_OFFSET, s.lastIndexOf("/"));
        } else {
            name = "unrecognized";
        }
        return name;
    }
}
