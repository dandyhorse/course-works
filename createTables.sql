Create table Repository (adress integer NOT NULL,
			 title_news varchar(150) NOT NULL,
			 login_adm  varchar(20) NOT NULL,
			 login_user  varchar(20) NOT NULL,
		     CONSTRAINT adress_pk PRIMARY KEY (adress),
		     CONSTRAINT rep_news_fk Foreign key (title_news) references "news" (title_news),
			 CONSTRAINT rep_adm_fk Foreign key (login_adm) references "administrator" (login_adm),
			 CONSTRAINT rep_user_fk Foreign key (login_user) references "userr" (login_user)
		     );
Create table News(title_news varchar(150) NOT NULL,
			 text varchar(150) NOT NULL,
		     coment varchar(150) NOT NULL,
			 CONSTRAINT news_pk PRIMARY KEY (title_news)
		      );
Create table Administrator (login_adm varchar(20) NOT NULL,
		     password integer NOT NULL,
		     CONSTRAINT administrator_pk Primary KEY (login_adm));
Create table Userr (login_user varchar(20) NOT NULL,
		     password integer NOT NULL,
		     CONSTRAINT user_pk Primary KEY (login_user));
			 
