-- tourists

INSERT INTO tourist (name, surname, father_name)
VALUES ('Hadoop', 'MapReduce', 'Yarnovich');

INSERT INTO tourist (name, surname, father_name)
VALUES ('Spark', 'Rdd', 'Batchevich');

INSERT INTO tourist (name, surname, father_name)
VALUES ('Flink', 'Calcite', 'Streamovich');

INSERT INTO tourist (name, surname, father_name)
VALUES ('Cassandra', 'HBase', 'Hivevich');

-- tours

INSERT INTO tour ( title, price, info)
VALUES ('Hawaii', 10, 'Hardly seems beautiful place...');

INSERT INTO tour (title, price, info)
VALUES ('Your home', 500, 'Too much resistance to get wasted there');

INSERT INTO tour (title, price, info)
VALUES ('Krakov', 1, 'Just ruins');

-- seasons

INSERT INTO season (tour_id, start_date, finish_date, finished, count_of_places)
VALUES (1, '2000-12-12', '2000-12-22', TRUE, 12);

INSERT INTO season (tour_id, start_date, finish_date, finished, count_of_places)
VALUES (2, '2016-12-12', '2017-12-22', FALSE, 12);

-- vouchers

INSERT INTO voucher (tourist_id, season_id)
VALUES (1, 2);

INSERT INTO voucher (tourist_id, season_id)
VALUES (2, 1);

-- payments

INSERT INTO payment (voucher_id, pay_day, amount)
VALUES (1, '2016-11-11', 500);

INSERT INTO payment (voucher_id, pay_day, amount)
VALUES (2, '2016-07-30', 10);

-- tourists_info

INSERT INTO tourist_info (tourist_id, passport, city, country, phone_number, index)
VALUES (1, 'A340345', 'Chensai', 'India', '87504564', 18898);

INSERT INTO tourist_info (tourist_id, passport, city, country, phone_number, index)
VALUES (2, '123456', 'Toronto', 'Canada', '189700546', 789456);

INSERT INTO tourist_info (tourist_id, passport, city, country, phone_number, index)
VALUES (3, '46019978', 'Samara', 'Russia', '89274567', 444089);

INSERT INTO tourist_info (tourist_id, passport, city, country, phone_number, index)
VALUES (4, 'C345F2166', 'Hong-Kong', 'China', '2362750', 987654);