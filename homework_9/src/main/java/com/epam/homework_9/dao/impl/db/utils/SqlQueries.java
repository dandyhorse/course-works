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
    //selects
    public static final String SELECT_ARTIST_BY_ID = getStringSelect(ARTISTS_TABLE, ARTIST_ID);
    public static final String SELECT_ALBUM_BY_ID = getStringSelect(ALBUMS_TABLE, ALBUM_ID);
    public static final String SELECT_TRACK_BY_ID = getStringSelect(TRACKS_TABLE, TRACK_ID);
    public static final String SELECT_TRACKS_FROM_ALBUM =
            getStringSelectFromAdj(TRACKS_TABLE, TRACK_ID, ADJ_TRACK_ID, ADJ_ALBUM_TRACK_TABLE, ADJ_ALBUM_ID);
    public static final String SELECT_ALBUMS_FROM_ARTIST =
            getStringSelectFromAdj(ALBUMS_TABLE, ALBUM_ID, ADJ_ALBUM_ID, ADJ_ARTIST_ALBUM_TABLE, ADJ_ARTIST_ID);

    //selects all
    public static final java.lang.String SELECT_ALL_ARTISTS = "SELECT * FROM " + ARTISTS_TABLE;

    //inserts
    public static final String INSERT_ARTIST = getStringInsert(ARTISTS_TABLE, ARTIST_ID, ARTIST_NAME);
    public static final String INSERT_ALBUM = getStringInsert(ALBUMS_TABLE, ALBUM_ID, ALBUM_TITLE, ALBUM_GENRE);
    public static final String INSERT_TRACK = getStringInsert(TRACKS_TABLE, TRACK_ID, TRACK_TITLE, TRACK_DURATION);
    public static final String INSERT_ADJ_ARTIST_ALBUM = getStringInsert(ADJ_ARTIST_ALBUM_TABLE, ADJ_ARTIST_ID, ADJ_ALBUM_ID);
    public static final String INSERT_ADJ_ALBUM_TRACK = getStringInsert(ADJ_ALBUM_TRACK_TABLE, ADJ_ALBUM_ID, ADJ_TRACK_ID);

    //deletes
    public static final String DELETE_ARTIST = getStringDelete(ARTISTS_TABLE, ARTIST_ID);
    public static final String DELETE_ALBUM = getStringDelete(ALBUMS_TABLE, ALBUM_ID);
    public static final String DELETE_TRACK = getStringDelete(TRACKS_TABLE, TRACK_ID);

    //update with conflict
    public static final String INSERT_WITH_CONFLICT_ADJ_ALBUM_TRACK = getStringInsertOnConflict(ADJ_ALBUM_TRACK_TABLE, ADJ_ALBUM_ID, ADJ_TRACK_ID);
    public static final String INSERT_WITH_CONFLICT_ADJ_ARTIST_ALBUM = getStringInsertOnConflict(ADJ_ARTIST_ALBUM_TABLE, ADJ_ARTIST_ID, ADJ_ALBUM_ID);

    //functions
    public static final String CALL_ARTIST_FUNCTION = "{CALL upsert_table_artist(?,?)}";
    public static final String CALL_ALBUM_FUNCTION = "{CALL upsert_table_album(?,?,?)}";
    public static final String CALL_TRACK_FUNCTION = "{CALL upsert_table_track(?,?,?)}";

    private static String getStringInsertOnConflict(String table, String firstId, String secondId) {
        return String.format("INSERT INTO %1$s(%2$s,%3$s) " +
                "VALUES(?,?) " +
                "ON CONFLICT(%2$s,%3$s) DO " +
                "UPDATE SET(%3$s) = (?)", table, firstId, secondId);
    }

    private static String getStringSelect(String table, String id) {
        return "SELECT * FROM " + table + " WHERE " + id + "=?";
    }

    private static String getStringSelectFromAdj(String table, String id, String sameId, String adjoiningTable, String adjoiningId) {
        return "SELECT * " +
                "FROM " + table +
                " WHERE " + id + " IN( " +
                "SELECT " + sameId + " " +
                "FROM " + adjoiningTable +
                " WHERE " + adjoiningId + "=?)";
    }

    private static String getStringInsert(String table, String id, String name) {
        return String.format("INSERT INTO %s(%s,%s) VALUES(?,?)", table, id, name);
    }

    private static String getStringInsert(String table, String id, String title, String duration) {
        return String.format("INSERT INTO %s(%s,%s,%s) VALUES(?,?,?)", table, id, title, duration);
    }

    private static String getStringDelete(String table, String id) {
        return "DELETE FROM " + table + " WHERE " + id + "=?";
    }

}
