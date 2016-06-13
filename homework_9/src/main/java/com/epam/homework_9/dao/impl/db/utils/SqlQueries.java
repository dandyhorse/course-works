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
    public static final String DELETE_ADJ_ARTIST_ALBUM = getStringDelete(ADJ_ARTIST_ALBUM_TABLE, ADJ_ARTIST_ID);
    public static final String DELETE_ADJ_ALBUM_TRACK = getStringDelete(ADJ_ALBUM_TRACK_TABLE, ADJ_ALBUM_ID);

    //updates
    public static final String UPDATE_ARTIST = getStringUpdate(ARTISTS_TABLE, ARTIST_ID, ARTIST_NAME);
    public static final String UPDATE_ALBUM = getStringUpdate(ALBUMS_TABLE, ALBUM_ID, ALBUM_TITLE, ALBUM_GENRE);
    public static final String UPDATE_TRACK = getStringUpdate(TRACKS_TABLE, TRACK_ID, TRACK_TITLE, TRACK_DURATION);

    public static final String INSERT_WITH_CONFLICT_ADJ_ALBUM_TRACK = getStringInsertOnConflict(ADJ_ALBUM_TRACK_TABLE, ADJ_ALBUM_ID, ADJ_TRACK_ID);
    public static final String INSERT_WITH_CONFLICT_ADJ_ARTIST_ALBUM = getStringInsertOnConflict(ADJ_ARTIST_ALBUM_TABLE, ADJ_ARTIST_ID, ADJ_ALBUM_ID);

    private static String getStringInsertOnConflict(String table, String firstId, String secondId) {
        return String.format("INSERT INTO %1$s(%2$s,%3$s) " +
                "VALUES(?,?) " +
                "ON CONFLICT(%2$s,%3$s) DO " +
                "UPDATE SET(%3$s) = (?)", table, firstId, secondId);
    }

    //drop constrains
    public static final String DROP_ADJ_ARTIST_CONSTRAINTS = getStringDropConstraints(ADJ_ARTIST_ALBUM_TABLE, ARTIST_CONSTRAINT);
    public static final String DROP_ADJ_ALBUM_IN_ARTISTS_CONSTRAINTS = getStringDropConstraints(ADJ_ARTIST_ALBUM_TABLE, ALBUM_CONSTRAINT);
    public static final String DROP_ADJ_ALBUM_IN_TRACKS_CONSTRAINTS = getStringDropConstraints(ADJ_ALBUM_TRACK_TABLE, ALBUM_CONSTRAINT);
    public static final String DROP_ADJ_TRACK_CONSTRAINTS = getStringDropConstraints(ADJ_ALBUM_TRACK_TABLE, TRACK_CONSTRAINT);

    //add constrains
    public static final String ALTER_ADJ_ARTIST_CONSTRAINTS = getStringAlterConstraints(
            ADJ_ARTIST_ALBUM_TABLE, ADJ_ARTIST_ID, ARTISTS_TABLE, ARTIST_ID, ARTIST_CONSTRAINT);
    public static final String ALTER_ADJ_ALBUM_IN_ARTISTS_CONSTRAINTS = getStringAlterConstraints(
            ADJ_ARTIST_ALBUM_TABLE, ADJ_ALBUM_ID, ALBUMS_TABLE, ALBUM_ID, ALBUM_CONSTRAINT);
    public static final String ALTER_ADJ_ALBUM_IN_TRACKS_CONSTRAINTS = getStringAlterConstraints(
            ADJ_ALBUM_TRACK_TABLE, ADJ_ALBUM_ID, ALBUMS_TABLE, ALBUM_ID, ALBUM_CONSTRAINT);
    public static final String ALTER_ADJ_TRACK_CONSTRAINTS = getStringAlterConstraints(
            ADJ_ALBUM_TRACK_TABLE, ADJ_TRACK_ID, TRACKS_TABLE, TRACK_ID, TRACK_CONSTRAINT);

    //functions
    public static final String CALL_ARTIST_FUNCTION = "{CALL upsert_table_artist(?,?)}";
    public static final String CALL_ALBUM_FUNCTION = "{CALL upsert_table_album(?,?,?)}";
    public static final String CALL_TRACK_FUNCTION = "{CALL upsert_table_track(?,?,?)}";

    private static String getStringAlterConstraints(String table, String adjoiningId, String refTable, String refId, String constraintName) {
        return "ALTER TABLE " + table +
                " ADD CONSTRAINT " + constraintName +
                " FOREIGN KEY (" + adjoiningId + ") REFERENCES \"" + refTable + "\" (" + refId + ")";
    }

    private static String getStringDropConstraints(String table, String constraintName) {
        return "ALTER TABLE " + table + " DROP CONSTRAINT " + constraintName;
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

    private static String getStringUpdate(String table, String id, String name) {
        return "UPDATE " + table + " SET " + name + "=? WHERE " + id + "=?";
    }

    private static String getStringUpdate(String table, String id, String title, String genre) {
        return "UPDATE " + table + " SET " + title + "=?, " + genre + "=? WHERE " + id + "=?";
    }

}
