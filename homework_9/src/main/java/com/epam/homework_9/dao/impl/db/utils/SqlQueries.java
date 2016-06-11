package com.epam.homework_9.dao.impl.db.utils;

import static com.epam.homework_9.dao.impl.db.utils.SqlColumns.*;

public class SqlQueries {

    //tables
    private static final String ARTISTS_TABLE = "artists";
    private static final String ALBUMS_TABLE = "albums";
    private static final String TRACKS_TABLE = "tracks";
    private static final String ADJ_ARTIST_ALBUM_TABLE = "adjoining_artist_album";
    private static final String ADJ_ALBUM_TRACK_TABLE = "adjoining_album_track";

    //queries
    public static final String SELECT_ARTIST_BY_ID = getStringSelect(ARTISTS_TABLE, ARTIST_ID);
    public static final String SELECT_ALBUM_BY_ID = getStringSelect(ALBUMS_TABLE, ALBUM_ID);
    public static final String SELECT_TRACK_BY_ID = getStringSelect(TRACKS_TABLE, TRACK_ID);

    private static String getStringSelect(String table, String id) {
        return "SELECT * FROM " + table + " WHERE " + id + "=?";
    }


    public static final String SELECT_TRACKS_FROM_ALBUM = "SELECT * " +
            "FROM " + TRACKS_TABLE +
            " WHERE " + TRACK_ID + " IN( " +
            "SELECT " + ADJ_TRACK_ID + " " +
            "FROM " + ADJ_ALBUM_TRACK_TABLE +
            " WHERE " + ADJ_ALBUM_ID + "=?)";

    public static final String SELECT_ALBUMS_FROM_ARTIST = "SELECT * " +
            "FROM " + ALBUMS_TABLE +
            " WHERE " + ALBUM_ID + " IN( " +
            "SELECT " + ADJ_ALBUM_ID + " " +
            "FROM " + ADJ_ARTIST_ALBUM_TABLE +
            " WHERE " + ADJ_ARTIST_ID + "=?)";

    public static final String INSERT_TRACK = String.format("INSERT INTO %s(%s,%s,%s) VALUES(?,?,?)",
            TRACKS_TABLE, TRACK_ID, TRACK_TITLE, TRACK_DURATION);

}
