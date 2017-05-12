CREATE TABLE IF NOT EXISTS `runs` (
	`id` integer NOT NULL AUTO_INCREMENT,
	`job_id` integer NOT NULL,
	`executed_at` binary(255) NOT NULL,
	`execution_time` long NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
