package com.epam.homework_9.utils;

import com.epam.homework_9.models.Album;
import com.epam.homework_9.models.Artist;
import com.epam.homework_9.models.MusicGuide;
import com.epam.homework_9.models.Track;

import java.time.Duration;

public class ContentProvider {

    public static void fillContent(MusicGuide guide) {
        Artist artistOne = getArtistSixto(1L);
        Artist artistTwo = getArtistBowie(2L);
        Artist artistThree = getArtistJimi(3L);
        guide.addArtist(artistOne);
        guide.addArtist(artistTwo);
        guide.addArtist(artistThree);
    }

    public static Artist getArtistSixto(Long id) {
        Track track = new Track(id, "Sugar Man", Duration.ofMinutes(3).plusSeconds(45));
        return Artist.newBuilder()
                .name("Sixto Rodriguez")
                .id(id)
                .addAlbum(Album.newBuilder()
                        .title("Cold Fact")
                        .genre("Soul")
                        .id(id)
                        .addTrack(track)
                        .build())
                .build();
    }

    public static Artist getTestArtist(Long id) {
        return Artist.newBuilder()
                .id(id)
                .name("test-artist")
                .addAlbum(Album.newBuilder()
                        .title("test-album")
                        .genre("test-genre")
                        .id(id)
                        .addTrack(new Track(id, "test-track", Duration.ofSeconds(1)))
                        .build())
                .build();
    }

    public static Artist getArtistBowie(Long id) {
        Track track1 = new Track(id, "Life on Mars", Duration.ofMinutes(3).plusSeconds(50));
        Track track2 = new Track(id + 1L, "The Man Who Sold the World", Duration.ofMinutes(5).plusSeconds(47));
        return Artist.newBuilder()
                .name("David Bowie")
                .id(id)
                .addAlbum(Album.newBuilder()
                        .id(id)
                        .title("Heroes")
                        .genre("Art Rock")
                        .addTrack(track1)
                        .addTrack(track2)
                        .build())
                .build();
    }

    public static Album getAddictiveAlbum(Long id) {
        Track track1 = new Track(id + 1L, "Chrono.Naut", Duration.ofMinutes(10).plusSeconds(8));
        Track track2 = new Track(id + 2L, "Funeralopolis", Duration.ofMinutes(4).plusSeconds(17));
        return Album.newBuilder()
                .title("Eternal")
                .genre("Doom metal")
                .addTrack(track1)
                .addTrack(track2)
                .id(id + 1L)
                .build();
    }

    public static Artist getArtistJimi(Long id) {
        Track track1 = new Track(id, "Who Knows", Duration.ofMinutes(4).plusSeconds(15));
        Track track2 = new Track(id + 1L, " Purple Haze", Duration.ofMinutes(5).plusSeconds(25));
        return Artist.newBuilder()
                .name("Jimi Hendrix")
                .id(id)
                .addAlbum(Album.newBuilder()
                        .title("Band of Gypsys")
                        .genre("Rock")
                        .addTrack(track1)
                        .addTrack(track2)
                        .id(id + 2L)
                        .build())
                .build();
    }

}
