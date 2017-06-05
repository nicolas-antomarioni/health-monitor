CREATE TABLE IF NOT EXISTS `applications` (
	`id` integer NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`base_url` varchar(200) NOT NULL,
	`info_enabled` boolean NOT NULL,
	`health_enabled` boolean NOT NULL,
	`active` boolean NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
