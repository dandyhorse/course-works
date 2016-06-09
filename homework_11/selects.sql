--названия и местоположения всех команд
SELECT title, city 
FROM teams;

--зарплата всех игроков команды
SELECT title "team title", SUM(b.fee) "all expenses"
FROM teams t 
LEFT JOIN players p 
ON t.id = p.id_team
LEFT JOIN biography b 
ON p.id = b.Id_player
GROUP BY title;

--отобразить всех русских игроков в бд
SELECT 
first_name, last_name 
FROM players p
LEFT JOIN biography b
ON p.id = b.id_player
WHERE b.country = 'RUS';


--средний рост и вес игрков команд
SELECT 
title "team title", 
round(AVG(b.weight), 2) "average weight", 
round(AVG(b.height),2) "average height"
FROM teams t 
LEFT JOIN players p 
ON t.id = p.id_team
LEFT JOIN biography b 
ON p.id = b.Id_player
GROUP BY title;

--получить количество игроков в каждой команде
SELECT 
title "team title",
COUNT(p)
FROM teams t
JOIN players p
ON p.id_team = t.id
GROUP BY "team title";