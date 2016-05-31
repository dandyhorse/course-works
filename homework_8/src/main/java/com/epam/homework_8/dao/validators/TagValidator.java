package com.epam.homework_8.dao.validators;

import com.epam.homework_8.dao.entity.TagAttributes;
import com.epam.homework_8.dao.exceptions.EntityException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagValidator {

    public static int validateTrackTag(String text) {
        String trackStringPattern = "(.*?(" + TagAttributes.TRACK_TAG + ")+(\\{)(.*?)(\\})(.*?))";
        Matcher matcher = getMatcher(text, trackStringPattern);
        Matcher attributeMatcher = getMatcher(text, "(.*?" + TagAttributes.NAME_TAG + " : .*?" + TagAttributes.DURATION_TAG + " : PT.+?\\})");
        int countOfTrack;
        if (validateCommon(text, trackStringPattern)) {
            countOfTrack = getCountMatchers(matcher);
            int countOfAttributes = getCountMatchers(attributeMatcher);
            if (countOfTrack != countOfAttributes) {
                throw new EntityException("tags Track{Name, Duration} is invalid");
            }
        } else {
            countOfTrack = 0;
        }
        return countOfTrack;
    }

    public static void validateArtistTag(String text) {
        String tag = TagAttributes.ARTIST_TAG;
        String tagPattern = ".*(" + tag + ")+(\\{)(.*)(\\})(.*)";
        if (!validateCommon(text, tagPattern)) {
            throw new EntityException("invalid " + tag + " tag in file");
        }
    }

    public static void validateMusicGuideTag(String text) {
        String tag = TagAttributes.MUSIC_GUIDE_TAG;
        String tagPattern = "(" + tag + ")(\\{)(.*)(\\})";
        if (!validateCommon(text, tagPattern)) {
            throw new EntityException("invalid " + tag + " tag  in file");
        }
    }

    //TODO correct validate album tag
    public static void validateAlbumTag(String text) {
        String tag = TagAttributes.ALBUM_TAG;
        String tagPattern = ".*(" + tag + ")+(\\{)(.*)(\\})(.*)";
        if (!validateCommon(text, tagPattern)) {
            throw new EntityException("invalid " + tag + " tag in file");
        }
    }

    private static boolean validateCommon(String text, String tag) {
        StringBuilder stringBuilder = new StringBuilder(text);
        String musicGuidePattern = "(MusicGuide)(\\{)(.*)(\\})";
        return Pattern.matches(tag, stringBuilder);
    }

    private static int getCountMatchers(Matcher attributeMatcher) {
        int i = 0;
        while (attributeMatcher.find()) {
            i++;
        }
        return i;
    }

    private static Matcher getMatcher(String text, String pattern) {
        Pattern attributePattern = Pattern.compile(pattern);
        return attributePattern.matcher(text);
    }
}
