package com.epam.homework_8.dao.validators;

import com.epam.homework_8.dao.exceptions.EntityException;

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
            throw new EntityException("invalid Artist{} tag in file");
        }
    }

    public static void validateMusicGuideTag(String text) {
        StringBuilder stringBuilder = new StringBuilder(text);
        String musicGuidePattern = "(MusicGuide)(\\{)(.*)(\\})";
        if (!Pattern.matches(musicGuidePattern, stringBuilder)) {
            throw new EntityException("invalid MusicGuide{} tag  in file");
        }
    }

    public static void validateAlbumTag(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        String artistPattern = ".*(Album)+(\\{)(.*)(\\})(.*)";
        if (!Pattern.matches(artistPattern, stringBuilder)) {
            throw new EntityException("invalid Album{} tag in file");
        }
    }
}
