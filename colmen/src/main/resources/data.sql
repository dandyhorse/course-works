
INSERT INTO Persons (Name)
VALUES ('Путин');
INSERT INTO Persons (Name)
VALUES ('Медведев');
INSERT INTO Persons (Name)
VALUES ('Навальный');

INSERT INTO Sites (Name)
VALUES ('https://lenta.ru');


INSERT INTO Keywords (Name, PersonID)
VALUES ('Путин', 1);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Путина', 1);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Путину', 1);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Путиным', 1);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Путине', 1);



INSERT INTO Keywords (Name, PersonID)
VALUES ('Медведев', 2);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Медведева', 2);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Медведеву', 2);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Медведевым', 2);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Медеведеве', 2);



INSERT INTO Keywords (Name, PersonID)
VALUES ('Навальный', 3);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Навального', 3);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Навальному', 3);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Навальным', 3);
INSERT INTO Keywords (Name, PersonID)
VALUES ('Навальном', 3);

INSERT INTO Pages
VALUES (1,'https://lenta.ru/news/2007/08/03/vodka/',1,'2016-08-28','2016-08-28'),
(2,'https://lenta.ru/news/2010/01/01/eu/',1,'2016-08-28','2016-08-28'),
(3,'https://lenta.ru/news/2010/01/01/aes/',1,'2016-08-28','2016-08-28'),
(4,'https://lenta.ru/news/2010/01/01/celebrate/',1,'2016-08-28','2016-08-28'),
(5,'https://lenta.ru/news/2010/01/01/three/',1,'2016-08-28','2016-08-28'),
(6,'https://lenta.ru/news/2010/01/01/free1/',1,'2016-08-28','2016-08-28'),
(7,'https://lenta.ru/news/2010/01/01/voice/',1,'2016-08-28','2016-08-28'),
(8,'https://lenta.ru/news/2010/01/01/charges/',1,'2016-08-28','2016-08-28'),
(9,'https://lenta.ru/news/2010/01/01/peace/',1,'2016-08-28','2016-08-28'),
(10,'https://lenta.ru/news/2010/01/01/osce/',1,'2016-08-28','2016-08-28');

INSERT INTO PersonPageRank (PersonID, PageID, Rank)
VALUES (3, 10, 9),
(2, 3, 12),
(1, 7, 0),
(2, 8, 27),
(3, 7, 1000);