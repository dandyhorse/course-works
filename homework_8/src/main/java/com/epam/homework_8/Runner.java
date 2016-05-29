package com.epam.homework_8;

import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;
import com.epam.homework_8.ser_models.MusicGuideProxy;
import com.epam.homework_8.serializers.Serializer;
import com.epam.homework_8.serializers.SerializerMusicGuideStream;

import java.time.Duration;
import java.util.Optional;


public class Runner {

    public void run() {
        String outputFile = "music_guide.txt";
        MusicGuide guide = new MusicGuide();
        fillContent(guide);

        MusicGuideProxy guideProxy = new MusicGuideProxy(guide);

        Serializer<MusicGuideProxy> serial = new SerializerMusicGuideStream();
        serial.serialize(outputFile, guideProxy);

        Optional<MusicGuideProxy> guideProxyOptional = serial.deserialize(outputFile);
        guideProxyOptional.ifPresent(musicGuideProxy -> {
            MusicGuide model = musicGuideProxy.getModel();
            System.out.println(model);
        });

    }


    private void fillContent(MusicGuide guide) {
        Artist artistOne = getArtistSixto();
        Artist artistTwo = getArtistTwo();
        guide.addArtist(artistOne);
        guide.addArtist(artistTwo);
    }

    private Artist getArtistTwo() {
        Track trackOne = new Track("Сияние", Duration.ofMinutes(2).plusSeconds(20));
        Track trackTwo = new Track("Невыносимая легкость бытия", Duration.ofMinutes(14).plusSeconds(53));
        return Artist.newBuilder()
                .setName("Гражданская Оборона")
                .addAlbum(
                        Album.newBuilder()
                                .setName("Зачем Снятся Сны")
                                .setGenre("Рок")
                                .addTrack(trackOne)
                                .addTrack(trackOne)
                                .build())
                .addAlbum(Album.newBuilder()
                        .setName("Солнцеворот")
                        .setGenre("Психодел Панк Рок")
                        .addTrack(trackTwo)
                        .build())
                .build();
    }

    private Artist getArtistSixto() {
        Track track = new Track("Sugar Man", Duration.ofMinutes(3).plusSeconds(45));
        return Artist.newBuilder()
                .setName("Sixto Rodriguez")
                .addAlbum(Album.newBuilder()
                        .setName("Cold Fact")
                        .setGenre("Soul")
                        .addTrack(track)
                        .build())
                .build();
    }
}
