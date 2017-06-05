CREATE TABLE IF NOT EXISTS `statuses` (
	`id` integer NOT NULL AUTO_INCREMENT,
	`application_id` integer NOT NULL,
	`retrieved_at` binary(255) NOT NULL,
	`message` varchar(200) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
