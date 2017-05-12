CREATE TABLE IF NOT EXISTS `schedulers_jobs` (
	`id` integer NOT NULL AUTO_INCREMENT,
	`scheduler_id` integer NOT NULL,
	`jobs_id` integer NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
