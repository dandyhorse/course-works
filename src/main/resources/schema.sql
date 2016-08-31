--
-- Структура таблицы `keywords`
--

CREATE TABLE IF NOT EXISTS `keywords` (
  `id` BIGINT NOT NULL,
  `name` varchar(2048) NOT NULL,
  `person_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Структура таблицы `pages`
--

CREATE TABLE IF NOT EXISTS `pages` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` varchar(2048) NOT NULL,
  `site_id` BIGINT NOT NULL,
  `found_date_time` date NOT NULL,
  `last_scan_date` date NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Структура таблицы `person_page_rank`
--

CREATE TABLE IF NOT EXISTS `person_page_rank` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `person_id` BIGINT NOT NULL,
  `page_id` BIGINT NOT NULL,
  `rank` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Структура таблицы `persons`
--

CREATE TABLE IF NOT EXISTS `persons` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор личности',
  `name` varchar(2048) NOT NULL COMMENT 'Наименование личности',
  PRIMARY KEY (`id`)
);

--
-- Структура таблицы `sites`
--

CREATE TABLE IF NOT EXISTS `sites` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`)
);
