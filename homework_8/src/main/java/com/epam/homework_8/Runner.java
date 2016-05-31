package com.epam.homework_8;

import com.epam.homework_8.dao.Dao;
import com.epam.homework_8.dao.DaoSerializer;
import com.epam.homework_8.dao.entity.MusicGuideEntity;
import com.epam.homework_8.dao.serializers.SerializerMusicGuideText;
import com.epam.homework_8.dao.serializers.SerializerObjectImpl;
import com.epam.homework_8.dao.serializers.interfaces.Serializer;
import com.epam.homework_8.models.Album;
import com.epam.homework_8.models.Artist;
import com.epam.homework_8.models.MusicGuide;
import com.epam.homework_8.models.Track;

import java.time.Duration;

public class Runner {

    public void run() {

        objectSerialization();

        MusicGuideSerialization();

    }

    private void MusicGuideSerialization() {
        String outputFile = "music_guide.ser";
        //model
        MusicGuide guide = new MusicGuide();
        fillContent(guide);
        //dao layer
        Serializer<MusicGuideEntity> serializer = new SerializerMusicGuideText();
        Dao<MusicGuide> dao = new DaoSerializer(outputFile, serializer);
        //serialize
        dao.save(guide);
        //deserialize
        MusicGuide guideFromFile = dao.get();

        System.out.println(guideFromFile);
    }

    private void objectSerialization() {
        String outputFileForStringSer = "string.ser";
        String s = System.getProperties().toString();
        Serializer<String> objectSer = new SerializerObjectImpl<>();
        objectSer.serialize(outputFileForStringSer, s);
        String deserializedString = objectSer.deserialize(outputFileForStringSer);
        System.out.println(deserializedString);
    }

    private void fillContent(MusicGuide guide) {
        Artist artistOne = getArtistSixto();
        Artist artistTwo = getArtistBowie();
        Artist artistThree = getArtistJimi();
        guide.addArtist(artistOne);
        guide.addArtist(artistTwo);
        guide.addArtist(artistThree);
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

    private Artist getArtistBowie() {
        Track track1 = new Track("Life on Mars", Duration.ofMinutes(3).plusSeconds(45));
        Track track2 = new Track("The Man Who Sold the World", Duration.ofMinutes(3).plusSeconds(45));
        return Artist.newBuilder()
                .setName("David Bowie")
                .addAlbum(Album.newBuilder()
                        .setName("Heroes")
                        .setGenre("Art Rock")
                        .addTrack(track1)
                        .addTrack(track2)
                        .build())
                .addAlbum(theSameAlbum())
                .build();
    }

    private Album theSameAlbum() {
        Track track1 = new Track("Chrono.Naut", Duration.ofMinutes(3).plusSeconds(45));
        Track track2 = new Track("Funeralopolis", Duration.ofMinutes(3).plusSeconds(45));
        return Album.newBuilder()
                .setName("Eternal")
                .setGenre("Doom metal")
                .addTrack(track1)
                .addTrack(track2)
                .build();
    }

    private Artist getArtistJimi() {
        Track track1 = new Track("Who Knows", Duration.ofMinutes(3).plusSeconds(45));
        Track track2 = new Track(" Purple Haze", Duration.ofMinutes(5).plusSeconds(45));
        return Artist.newBuilder()
                .setName("Jimi Hendrix")
                .addAlbum(Album.newBuilder()
                        .setName("Band of Gypsys")
                        .setGenre("Rock")
                        .addTrack(track1)
                        .addTrack(track2)
                        .build())
                .addAlbum(theSameAlbum())
                .build();
    }
}
