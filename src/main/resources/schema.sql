CREATE TABLE IF NOT EXISTS seller (
  id           BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  fio          VARCHAR(40) NOT NULL,
  phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS customer (
  id           BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  fio          VARCHAR(20) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  job          VARCHAR(20) NOT NULL,
  post         VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS address (
  id          BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  region      VARCHAR(20) NOT NULL,
  street      VARCHAR(20) NOT NULL,
  home_number INT         NOT NULL,
  flat_number INT
);

CREATE TABLE IF NOT EXISTS flat (
  id             BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type_home      VARCHAR(20) NOT NULL,
  common_space   INT         NOT NULL,
  resident_space INT         NOT NULL,
  id_address     BIGINT      NOT NULL,
  CONSTRAINT flat_fk FOREIGN KEY (id_address) REFERENCES address (id),
);

CREATE TABLE IF NOT EXISTS trade (
  id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  trade_date  TIMESTAMP   NOT NULL,
  cost        BIGINT NOT NULL,
  id_customer BIGINT NOT NULL,
  id_seller   BIGINT NOT NULL,
  id_flat     BIGINT NOT NULL,
  CONSTRAINT trade_fk FOREIGN KEY (id_customer) REFERENCES customer (id),
  CONSTRAINT trade_fk2 FOREIGN KEY (id_seller) REFERENCES seller (id),
  CONSTRAINT trade_fk3 FOREIGN KEY (id_flat) REFERENCES flat (id)
);


