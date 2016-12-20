INSERT INTO seller (id, fio, phone_number) VALUES (1, 'Roger Kovalsky', '098765432B1');
INSERT INTO seller (id, fio, phone_number) VALUES (2, 'Stephan Ewen?', '1234567890a');

INSERT INTO customer (id, fio, phone_number, job, post) VALUES (1, 'Somebody someonovich', '1234567890a', 'Engineer', 'CEO');
INSERT INTO customer (id, fio, phone_number, job, post) VALUES (2, 'Stephan Ewen', '1234567890a', 'Engineer', 'CEO');

INSERT INTO address (id, region, street, home_number, flat_number) VALUES (1, 'Ont', 'Mr.Mr', '88', '55');
INSERT INTO address (id, region, street, home_number, flat_number) VALUES (2, 'Tor', 'Wow.Wow', '72', '27');


INSERT INTO flat (id, type_home, common_space, resident_space, id_address) VALUES (1, 'vila', 33, 33, 1);
INSERT INTO flat (id, type_home, common_space, resident_space, id_address) VALUES (2, 'garbage bucket', 2, 1, 2);

INSERT INTO trade (id, trade_date, cost, id_customer, id_seller, id_flat) VALUES (1, '2015-12-10', 20000, 2, 2, 1);
INSERT INTO trade (id, trade_date, cost, id_customer, id_seller, id_flat) VALUES (2, '2008-12-1', 100, 1, 1, 2);

