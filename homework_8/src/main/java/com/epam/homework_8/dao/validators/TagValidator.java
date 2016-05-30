package com.epam.homework_8.dao.validators;

import java.util.regex.Pattern;

public class TagValidator {

    public static boolean validateTrackTag(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        String artistPattern = ".*(Track)+(\\{)(.*)(\\})(.*)";
        return Pattern.matches(artistPattern, stringBuilder);
    }

    public static void validateArtistTag(String text) {
        StringBuilder stringBuilder = new StringBuilder(text);
        String artistPattern = ".*(Artist)+(\\{)(.*)(\\})(.*)";
        if (!Pattern.matches(artistPattern, stringBuilder)) {
            //TODO custom exception
            throw new RuntimeException();
        }
    }

    public static void validateMusicGuideTag(String text) {
        StringBuilder stringBuilder = new StringBuilder(text);
        String musicGuidePattern = "(MusicGuide)(\\{)(.*)(\\})";
        if (!Pattern.matches(musicGuidePattern, stringBuilder)) {
            //TODO custom exception
            throw new RuntimeException();
        }
    }

    public static void validateAlbumTag(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        String artistPattern = ".*(Album)+(\\{)(.*)(\\})(.*)";
        if (!Pattern.matches(artistPattern, stringBuilder)) {
            //TODO custom exception
            throw new RuntimeException();
        }
    }
}
