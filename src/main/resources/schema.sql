CREATE TABLE IF NOT EXISTS tourist (
	id bigint not null primary key auto_increment,
	surname varchar(20) not null,
	name varchar(20) not null,
	father_name varchar(20) not null
);

CREATE TABLE IF NOT EXISTS tour (
	id bigint not null primary key auto_increment,
	title varchar(20) not null,
	price int not null,
	info varchar(64)
);

CREATE TABLE IF NOT EXISTS season (
	id bigint not null primary key auto_increment,
	tour_id bigint not null,
	start_date date not null,
	finish_date date not null,
	finished boolean not null,
	count_of_places int not null,
	foreign key (tour_id) references tour (id)
);

CREATE TABLE IF NOT EXISTS voucher (
	id bigint not null primary key auto_increment,
	tourist_id bigint not null,
	season_id bigint not null,
	foreign key (tourist_id) references tourist (id),
	foreign key (season_id) references season (id)
);

CREATE TABLE IF NOT EXISTS payment (
  id bigint not null primary key,
	voucher_id bigint not null,
	pay_day date not null,
	amount int not null,
	foreign key (voucher_id) references voucher (id)
);

CREATE TABLE IF NOT EXISTS tourist_info (
  id bigint not null primary key auto_increment,
	tourist_id bigint not null,
	passport varchar(10) not null,
	city varchar(20) not null,
	country varchar(20) not null,
	phone_number int not null,
	index int not null,
	foreign key (tourist_id) references tourist (id)
);






