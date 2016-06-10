BEGIN;
CREATE TABLE "tracks" (
  id       SERIAL PRIMARY KEY,
  title    TEXT        NOT NULL,
  duration VARCHAR(10) NOT NULL
);

CREATE TABLE "artists" (
  id         BIGSERIAL PRIMARY KEY,
  first_name TEXT   NOT NULL,
  last_name  TEXT   NOT NULL
);
CREATE TABLE "albums" (
  id    BIGSERIAL PRIMARY KEY,
  name  TEXT        NOT NULL,
  genre VARCHAR(30) NOT NULL
);
CREATE TABLE "adjoining_artist_album" (
  id_artist BIGINT,
  id_album  BIGINT,
  FOREIGN KEY (id_artist) REFERENCES "artists" (id),
  FOREIGN KEY (id_album) REFERENCES "albums" (id)
);
CREATE TABLE "adjoining_album_track" (
  id_album BIGINT,
  id_track BIGINT,
  FOREIGN KEY (id_album) REFERENCES "albums" (id),
  FOREIGN KEY (id_track) REFERENCES "tracks" (id)
);
COMMIT;