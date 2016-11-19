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

INSERT INTO tour (id, title, price, info)
VALUES (1, 'Hawaii', 10, 'Hardly seems beautiful place...');

INSERT INTO tour (title, price, info)
VALUES ('Your home', 500, 'Too much resistance to get wasted there');

INSERT INTO tour (title, price, info)
VALUES ('Krakov', 1, 'Just ruins');

INSERT INTO season (tour_id, start_date, finish_date, finished, count_of_places)
VALUES (1, '2000-12-12', '2000-12-22', TRUE, 12);

INSERT INTO season (tour_id, start_date, finish_date, finished, count_of_places)
VALUES (2, '2016-12-12', '2017-12-22', FALSE, 12);