package com.epam.homework_8.dao.validators;

import com.epam.homework_8.dao.entity.tags.Tags;
import com.epam.homework_8.dao.exceptions.EntityException;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TagValidator {

    public static void validateTrackTag(String text) {
        if (text.trim().length()==0){
            return;
        }
        String fastTagPattern = String.format(".*?(%s\\{.*?%s : .*?%s : .*?\\}.*?)*?", Tags.TRACK_TAG, Tags.NAME_ATTR, Tags.DURATION_ATTR);

        findAtLeastOneTag(text, fastTagPattern);
        deepValidationTag(text, "\\{.*?}");
    }

    public static void validateMusicGuideTag(String text) {
        String tagPattern = "(^" + Tags.MUSIC_GUIDE_TAG + ")(\\{)(.*)(\\}$)";
        findAtLeastOneTag(text, tagPattern);
    }

    public static void validateAlbumTag(String text) {
        String fastTagPattern = ".*(" + Tags.ALBUM_TAG + ")+(\\{)(.*" + Tags.NAME_ATTR + " : .+?\\})(.*)";
        findAtLeastOneTag(text, fastTagPattern);
        String excludePattern = "(\\{.*?(\\{.*?\\}.*?)*?\\})+";
        deepValidationTag(text, excludePattern);
    }

    public static void validateArtistTag(String text) {
        String fastTagPattern = "(.*)(" + Tags.ARTIST_TAG + "\\{)+(.+?" + Tags.NAME_ATTR + " : .+?\\})";
        findAtLeastOneTag(text, fastTagPattern);
        String excludePattern = "(\\{.*?(\\{.*?\\{.*?\\}.*?\\})*?.*?\\})+?";
        deepValidationTag(text, excludePattern);
    }

    public static void isStringTag(String s) {
        switch (s) {
            case Tags.ALBUM_TAG:
                return;
            case Tags.ARTIST_TAG:
                return;
            case Tags.TRACK_TAG:
                return;
            default:
                throw new EntityException("invalid " + Tags.ARTIST_TAG + " tag in file");
        }
    }

    private static void findAtLeastOneTag(String text, String fastTagPattern) {
        if (!commonValidation(text, fastTagPattern)) {
            throw new EntityException("invalid tag in file");
        }
    }

    private static void deepValidationTag(String text, String excludePattern) {
        Stream<String> stringStream = Stream.of(text
                .replaceAll(excludePattern, "")
                .split("\\W"));
        stringStream.filter(s -> !s.isEmpty()).forEach(TagValidator::isStringTag);
    }

    private static boolean commonValidation(String text, String tag) {
        StringBuilder stringBuilder = new StringBuilder(text);
        return Pattern.matches(tag, stringBuilder);
    }

}
