CREATE TABLE news (

  id     SERIAL       NOT NULL,
  title  VARCHAR(150) NOT NULL,
  text   VARCHAR(300) NOT NULL,
  coment VARCHAR(150) NOT NULL,

  CONSTRAINT id_pk PRIMARY KEY (id)
);

CREATE TABLE admin (
  login    VARCHAR(20) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,

  CONSTRAINT administrator_pk PRIMARY KEY (login)
);

CREATE TABLE uzer (
  login    VARCHAR(20) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,

  CONSTRAINT user_pk PRIMARY KEY (login)
);

CREATE TABLE Repository (
  id    SERIAL       NOT NULL,
  news_id  VARCHAR(150) NOT NULL,
  admin_login VARCHAR(20)  NOT NULL,
  uzer_login  VARCHAR(20)  NOT NULL,

  CONSTRAINT id_pk PRIMARY KEY (id),
  CONSTRAINT rep_news_fk FOREIGN KEY (news_id) REFERENCES "news" (id),
  CONSTRAINT rep_adm_fk FOREIGN KEY (admin_login) REFERENCES "admin" (login),
  CONSTRAINT rep_user_fk FOREIGN KEY (uzer_login) REFERENCES "uzer" (login)
);
			 
