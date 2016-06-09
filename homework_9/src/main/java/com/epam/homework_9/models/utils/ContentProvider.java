package com.epam.homework_9.models.utils;

import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import com.epam.homework_9.models.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class ContentProvider {

    public static void fillContent(MusicGuide guide) {
        Artist artistOne = getArtistSixto();
        Artist artistTwo = getArtistBowie();
        Artist artistThree = getArtistJimi();
        guide.addArtist(artistOne);
        guide.addArtist(artistTwo);
        guide.addArtist(artistThree);
    }

    public static Artist getArtistSixto() {
        Track track = new Track(1L, "Sugar Man", Duration.ofMinutes(3).plusSeconds(45));
        return Artist.newBuilder()
                .name("Sixto Rodriguez")
                .id(1L)
                .addAlbum(Album.newBuilder()
                        .name("Cold Fact")
                        .genre("Soul")
                        .id(1L)
                        .addTrack(track)
                        .build())
                .build();
    }

    public static Artist getArtistBowie() {
        Track track1 = new Track(1L, "Life on Mars", Duration.ofMinutes(3).plusSeconds(45));
        Track track2 = new Track(2L, "The Man Who Sold the World", Duration.ofMinutes(3).plusSeconds(45));
        return Artist.newBuilder()
                .name("David Bowie")
                .id(2L)
                .addAlbum(Album.newBuilder()
                        .name("Heroes")
                        .genre("Art Rock")
                        .addTrack(track1)
                        .addTrack(track2)
                        .id(1L)
                        .build())
                .build();
    }

    public static Album getAddictiveAlbum() {
        Track track1 = new Track(3L, "Chrono.Naut", Duration.ofMinutes(3).plusSeconds(45));
        Track track2 = new Track(4L, "Funeralopolis", Duration.ofMinutes(3).plusSeconds(45));
        return Album.newBuilder()
                .name("Eternal")
                .genre("Doom metal")
                .addTrack(track1)
                .addTrack(track2)
                .id(2L)
                .build();
    }

    public static Artist getArtistJimi() {
        Track track1 = new Track(1L, "Who Knows", Duration.ofMinutes(3).plusSeconds(45));
        Track track2 = new Track(2L, " Purple Haze", Duration.ofMinutes(5).plusSeconds(45));
        return Artist.newBuilder()
                .name("Jimi Hendrix")
                .id(1L)
                .addAlbum(Album.newBuilder()
                        .name("Band of Gypsys")
                        .genre("Rock")
                        .addTrack(track1)
                        .addTrack(track2)
                        .id(1L)
                        .build())
                .build();
    }

}
