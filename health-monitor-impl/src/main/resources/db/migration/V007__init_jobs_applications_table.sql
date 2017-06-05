CREATE TABLE IF NOT EXISTS `jobs_applications` (
	`id` integer NOT NULL AUTO_INCREMENT,
	`job_id` integer NOT NULL,
	`applications_id` integer NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
