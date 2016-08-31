--
-- Дамп данных таблицы `pages`
--

INSERT INTO `pages` (`id`, `url`, `Site_id`, `found_date_time`, `last_scan_date`)
VALUES
(1, 'http://lenta.ru/rubrics/world/', 1, '2016-08-30', '2016-08-29'),
(2, 'http://lenta.ru/rubrics/world/', 1, '2016-08-30', '2016-08-29');


--
-- Дамп данных таблицы `person_page_rank`
--

INSERT INTO `person_page_rank` (`person_id`, `page_id`, `rank`)
VALUES
(1, 1, 2),
(2, 2, 1);

--
-- Дамп данных таблицы `persons`
--

INSERT INTO `persons` (`id`, `name`)
VALUES
(1, 'Путин'),
(2, 'Медведев');

--
-- Дамп данных таблицы `sites`
--

INSERT INTO `sites` (`id`, `name`)
VALUES
(1, 'lenta.ru'),
(2, 'rbc.ru');