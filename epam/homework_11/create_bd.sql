BEGIN;
CREATE TABLE "teams" (
  id SERIAL PRIMARY KEY,
  title   TEXT,
  city    TEXT,
  UNIQUE(title)
);
CREATE TABLE "players" (
  id  BIGSERIAL PRIMARY KEY,
  first_name TEXT,
  last_name  TEXT,
  id_team    INTEGER,
  FOREIGN KEY (id_team) REFERENCES "teams" (id)
);
CREATE TABLE "biography" (
  id BIGSERIAL PRIMARY KEY,
  height       SMALLINT,
  weight       SMALLINT,
  birth_date   DATE,
  priority     SMALLINT,
  fee          MONEY,
  country      VARCHAR(3),
  year_as_pro  SMALLINT,
  id_player    BIGINT,
  FOREIGN KEY (id_player) REFERENCES "players" (id),
  CHECK (priority > 0 AND priority <= 4),
  CHECK (height > 0 AND  weight > 0),
  CHECK (year_as_pro > 0)
);
COMMIT;
-- insert 5 teams
BEGIN;
INSERT INTO teams (id, title, city) 
VALUES (0, 'Rangers', 'New York');
INSERT INTO teams (id, title, city) 
VALUES (1, 'Delhi Waveriders', 'Delhi');
INSERT INTO teams (id, title, city) 
VALUES (2, 'Red Wings', 'Detroit');
INSERT INTO teams (id, title, city) 
VALUES (3, 'Blackhawks', 'Chicago');
INSERT INTO teams (id, title, city) 
VALUES (4, 'Penguins', 'Pittsburgh');
COMMIT;

-- insert players in team Rangers
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (0, 'Wayne', 'Gretzky', 0);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (0, 183, 84, '1994-04-08', 1, 600000, 'CAN', 17, 0);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (1, 'Vova', 'Kildushev', 0);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (1, 173, 92, '2016-04-06', 3, 250000, 'USA', 5, 1);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (2, 'Conor', 'McGregor', 0);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (2, 175, 80, '1988-04-08', 1, 1000000, 'IRL', 5, 2);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (3, 'Anton', 'Solovev', 0);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (3, 181, 74, '1994-10-10', 2, 405000, 'RUS', 6, 3);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (4, 'Rajesh', 'Kutrapali', 0);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (4, 200, 77, '2000-04-10', 4, 10000, 'IND', 3, 4);
COMMIT;
-- insert players in team Delhi Waveriders
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (5, 'Rajesh', 'Kutrapali', 1);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (5, 200, 63, '2000-12-01', 2, 10000, 'IND', 3, 5);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (6, 'Rupinder', 'Pal Singh', 1);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (6, 199, 74, '1999-05-11', 2, 13000, 'IND', 4, 6);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (7, 'Parvinder', 'Singh', 1);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (7, 160, 72, '2001-01-01', 3, 7000, 'IND',2, 7);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (8, 'Tristen', 'Oliver White', 1);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (8, 179, 59, '2002-01-11', 4, 5000, 'AUS', 1, 8);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (9, 'Iain', 'Wallace Lewers', 1);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (9, 180, 90, '1997-05-20', 1, 20000, 'ENG', 6, 9);
COMMIT;
-- insert players in team Red Wings
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (10, 'Logan', 'Couture', 2);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (10, 178, 85, '1989-12-01', 1, 700000, 'USA', 7, 10);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (11, 'Joe', 'Pavelski', 2);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (11, 180, 96, '1985-11-13', 1, 660000, 'ROM', 5, 11);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (12, 'Evgeni', 'Malkin', 2);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (12, 195, 90, '1988-12-01', 2, 710000, 'RUS', 6, 12);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (13, 'Nikita', 'Kucherov', 2);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (13, 178, 78, '1984-11-12', 1, 600000, 'RUS', 9, 13);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team) 
VALUES (14, 'Sidney', 'Crosby', 2);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (14, 178, 73, '1990-04-01', 1, 700000, 'CAN', 7, 14);
COMMIT;
-- insert players in team Blackhawks
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (15, 'Christian', 'Ehrhoff', 3);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (15, 169, 85, '1987-04-18', 1, 540000, 'GER', 8, 15);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (16, 'Marian', 'Hossa', 3);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (16, 177, 91, '1988-08-07', 3, 440000, 'CHE', 5, 16);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (17, 'David', 'Rundblad', 3);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (17, 179, 87, '1983-12-08', 2, 590000, 'SWE', 7, 17);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (18, 'Artem', 'Anisimov', 3);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (18, 179, 75, '1989-04-07', 1, 590000, 'RUS', 9, 18);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (19, 'Dale ', 'Weise', 3);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (19, 166, 73, '1996-06-06', 4, 666000, 'CAN', 4, 19);
COMMIT;
-- insert players in team Penguins
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (20, 'Frederik', 'Andersen', 4);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (20, 187, 69, '1983-03-27', 2, 550000, 'DEN', 10, 20);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (21, 'Guy', 'Hebert', 4);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (21, 183, 90, '1985-10-05', 1, 600000, 'USA', 11, 21);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (22, 'Antti', 'Aalto', 4);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (22, 187, 78, '1994-03-27', 3, 450000, 'CAN', 5, 22);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (23, 'Rene', 'Bourque', 4);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (23, 183, 74, '1989-10-14', 2, 490000, 'FRA', 10, 23);
COMMIT;
BEGIN;
INSERT INTO players (id, first_name, last_name, id_team)
VALUES (24, 'Tom', 'Askey', 4);
INSERT INTO biography (id, height, weight, birth_date, priority, fee, country, year_as_pro, id_player)
VALUES (24, 181, 87, '1983-03-27', 4, 400000, 'USA', 4, 24);
COMMIT;