CREATE TABLE IF NOT EXISTS `schedulers` (
	`id` integer NOT NULL AUTO_INCREMENT,
	`name` varchar(30) DEFAULT NULL,
	`description` varchar(200) DEFAULT NULL,
	`last_run` binary(255) NOT NULL,
	`interval` long NOT NULL,
	`active` boolean NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
