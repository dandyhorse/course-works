package com.epam.homework_8;

import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;
import com.epam.homework_8.ser_models.MusicGuideProxy;
import com.epam.homework_8.serializers.Serializer;
import com.epam.homework_8.serializers.SerializerMusciGuideText;

import java.time.Duration;


public class Runner {

    public void run() {
        String outputFile = "music_guide.ser";
        MusicGuide guide = new MusicGuide();
        fillContent(guide);

        MusicGuideProxy guideProxy = new MusicGuideProxy(guide);

        Serializer<MusicGuideProxy> serial = new SerializerMusciGuideText();
        serial.serialize(outputFile, guideProxy);

//        Optional<MusicGuideProxy> guideProxyOptional = serial.deserialize(outputFile);
//        guideProxyOptional.ifPresent(musicGuideProxy -> {
//            MusicGuide model = musicGuideProxy.getModel();
//            System.out.println(model);
//        });

    }


    private void fillContent(MusicGuide guide) {
        Artist artistOne = getArtistSixto();
        Artist artistTwo = getArtistGrob();
        guide.addArtist(artistOne);
        guide.addArtist(artistTwo);
    }

    private Artist getArtistGrob() {
        Track trackOne = new Track("Сияние", Duration.ofMinutes(2).plusSeconds(20));
        Track trackTwo = new Track("Невыносимая легкость бытия", Duration.ofMinutes(14).plusSeconds(53));
        return Artist.newBuilder()
                .setName("Гражданская Оборона")
                .addAlbum(
                        Album.newBuilder()
                                .setName("Зачем Снятся Сны")
                                .setGenre("Рок")
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
