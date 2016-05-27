package com.epam.homework_8;

import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;
import com.epam.homework_8.ser_models.MusicGuideProxy;
import com.epam.homework_8.serializers.Serializer;
import com.epam.homework_8.serializers.SerializerMusicGuide;

import java.time.Duration;
import java.util.Optional;


public class Runner {

    public void run() {
        String outputFile = "music_guide.txt";
        MusicGuide guide = new MusicGuide();
        fillContent(guide);

        MusicGuideProxy guideProxy = new MusicGuideProxy(guide);

        Serializer<MusicGuideProxy> serial = new SerializerMusicGuide();
        serial.serialize(outputFile, guideProxy);

        Optional<MusicGuideProxy> guideProxyOptional = serial.deserialize(outputFile);
        guideProxyOptional.ifPresent(musicGuideProxy -> {
            MusicGuide model = musicGuideProxy.getModel();
            System.out.println(model);
        });

    }


    private void fillContent(MusicGuide guide) {
        Artist artistOne = getArtistOne();
        Artist artistTwo = getArtistTwo();
        guide.addArtist(artistOne);
        guide.addArtist(artistTwo);
    }

    private Artist getArtistTwo() {
        Track track = new Track("Yellow Submarine", Duration.ofMinutes(3));
        return Artist.newBuilder()
                .setName("Beatles")
                .addAlbum(
                        Album.newBuilder()
                                .setName("White")
                                .setGenre("Pop")
                                .addTrack(track)
                                .build())
                .build();
    }

    private Artist getArtistOne() {
        Track track = new Track("Sugar Man", Duration.ofMinutes(4).plusSeconds(15));
        return Artist.newBuilder()
                .setName("Sixto")
                .addAlbum(Album.newBuilder()
                        .setName("Rodrigez")
                        .setGenre("Folk")
                        .addTrack(track)
                        .build())
                .build();
    }
}
