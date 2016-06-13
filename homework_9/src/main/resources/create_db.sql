BEGIN;
CREATE TABLE "tracks" (
  id       SERIAL PRIMARY KEY,
  title    TEXT        NOT NULL,
  duration VARCHAR(10) NOT NULL
);

CREATE TABLE "artists" (
  id          BIGSERIAL PRIMARY KEY,
  name TEXT   NOT NULL
);
CREATE TABLE "albums" (
  id     BIGSERIAL PRIMARY KEY,
  title  TEXT        NOT NULL,
  genre  VARCHAR(30) NOT NULL
);
CREATE TABLE "adjoining_artist_album" (
  id_artist BIGINT,
  id_album  BIGINT,
  CONSTRAINT c_artist FOREIGN KEY (id_artist) REFERENCES "artists" (id),
  CONSTRAINT c_album FOREIGN KEY (id_album) REFERENCES "albums" (id),
  UNIQUE (id_artist, id_album)
);
CREATE TABLE "adjoining_album_track" (
  id_album BIGINT,
  id_track BIGINT,
  CONSTRAINT c_album FOREIGN KEY (id_album) REFERENCES "albums" (id),
  CONSTRAINT c_track FOREIGN KEY (id_track) REFERENCES "tracks" (id),
  UNIQUE (id_album, id_track)
);
CREATE OR REPLACE FUNCTION upsert_table_artist(argId BIGINT, argName TEXT)
  RETURNS void AS
$BODY$
DECLARE
BEGIN
    UPDATE artists SET id = argId, name = argName WHERE id = argId;
    IF NOT FOUND THEN
    INSERT INTO artists(id, name) values (argId, argName);
    END IF;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE;
CREATE OR REPLACE FUNCTION upsert_table_album(argId BIGINT, argTitle TEXT, argGenre TEXT)
  RETURNS void AS
$BODY$
DECLARE
BEGIN
    UPDATE albums SET id = argId, title = argTitle, genre = argGenre WHERE id = argId;
    IF NOT FOUND THEN
    INSERT INTO albums (id, title, genre) values (argId, argTitle, argGenre);
    END IF;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE;
  CREATE OR REPLACE FUNCTION upsert_table_track(argId BIGINT, argTitle TEXT, argDur TEXT)
    RETURNS void AS
  $BODY$
  DECLARE
  BEGIN
      UPDATE tracks SET id = argId, title = argTitle, duration = argDur WHERE id = argId;
      IF NOT FOUND THEN
      INSERT INTO tracks(id,title,duration) values (argId, argTitle, argDur);
      END IF;
  END;
  $BODY$
    LANGUAGE plpgsql VOLATILE;
COMMIT;