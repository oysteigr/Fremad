CREATE TABLE `team` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) DEFAULT NULL,
	`online_id` int(11) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `player` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`first_name` varchar(255) NOT NULL,
	`last_name` varchar(255) NOT NULL,
	`number` int(6) NOT NULL,
	`member_since` year(4) NOT NULL,
	`position` varchar(64) NOT NULL,
	`preferred_foot` enum('left', 'right'),
	`team` int(11) NOT NULL,
	`active` tinyint(1) NOT NULL DEFAULT 0,
	CONSTRAINT `fk_player_team` FOREIGN KEY (`team`) REFERENCES `team` (`id`),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `player_note` (
	`id` int(16) NOT NULL AUTO_INCREMENT,
	`player_id` int(11) NOT NULL,
	`note` varchar(255) NOT NULL,
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT `fk_player_note` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `league` (
	`id` int(11) NOT NULL,
	`name` varchar(255),
	`year` year(4) DEFAULT NULL,
	`team` int(11) DEFAULT NULL,
	CONSTRAINT `fk_league_team` FOREIGN KEY (`team`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `match` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`league` int(11) NOT NULL,
	`fremad_team` int(11) NOT NULL,
	`home_match` tinyint(1) NOT NULL,
	`fremad_goals` tinyint DEFAULT NULL,
	`opposing_team_name` varchar(255) NOT NULL,
	`opposing_team_id` int(11) NOT NULL,
	`opposing_team_goals` tinyint DEFAULT NULL,
	`date` timestamp NOT NULL,
	`field` varchar(255) NOT NULL,
	CONSTRAINT `fk_match_team` FOREIGN KEY (`fremad_team`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `fk_match_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `uc_foreign_team` UNIQUE (`date`, `opposing_team_id`),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `event` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`type` ENUM('GOAL', 'GOAL_AGAINST', 'ASSIST', 'SUB_IN', 'SUB_OUT', 'RED_CARD', 'YELLOW_CARD', 'COMMENT') NOT NULL,
	`player` int(11),
	`match` int(11) NOT NULL,
	`time` tinyint NOT NULL,
	`comment` varchar(255),
	`parent` int(11),
	CONSTRAINT `fk_event_match` FOREIGN KEY (`match`) REFERENCES `match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `fk_event_player` FOREIGN KEY (`player`) REFERENCES `player` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `fk_event_parent` FOREIGN KEY (`parent`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `table_entry` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`league_id` int(11) NOT NULL,
	`pos` tinyint NOT NULL,
	`team_name` varchar(255) NOT NULL,
	`team_id` int(11) NOT NULL,
	`match_count` tinyint NOT NULL,
	`goals_scored` int(8) NOT NULL,
	`goals_conceded` int(8) NOT NULL,
	`points` int(8) NOT NULL,
	`games_won` int(6) NOT NULL,
	`games_tied` int(6) NOT NULL,
	`games_lost` int(6) NOT NULL,
	CONSTRAINT `fk_table_entry_league` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `uc_league_team` UNIQUE (`league_id`, `team_id`),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE `user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`user_name` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`salt` varchar(255) NOT NULL,
	`role` ENUM('SUPPORTER', 'PLAYER', 'AUTHOR', 'EDITOR', 'ADMIN', 'SUPER') NOT NULL DEFAULT 'SUPPORTER',
	`created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`validated` tinyint(1) NOT NULL DEFAULT 0,
	CONSTRAINT `uc_user_name` UNIQUE (`user_name`),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role_request` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL,
	`requested_role` ENUM('SUPPORTER', 'PLAYER', 'AUTHOR', 'EDITOR', 'ADMIN', 'SUPER') NOT NULL,
	`date` timestamp NOT NULL,
	`accepted` tinyint(1) NOT NULL DEFAULT 0,
	`accepted_by` int(11) NOT NULL DEFAULT -1,
	CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_login` (
	`id` int(16) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL,
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT `fk_login_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_meta` (
	 `user_id` INT(11) NOT NULL , 
	 `first_name` VARCHAR(32) NOT NULL , 
	 `last_name` VARCHAR(32) NOT NULL , 
	 `phone_number` VARCHAR(16) , 
	 `birthday` DATE DEFAULT '0001-01-01',
	 `home_town` VARCHAR(32) , 
	 `profession` VARCHAR(32) , 
	 CONSTRAINT `uc_user_id` UNIQUE (`user_id`),
	 FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_validation` (
	`user_id` int(11) NOT NULL,
	`code` varchar(255) NOT NULL,
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT `uc_pw_user_id` UNIQUE (`user_id`),
	FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_forgot_password` (
	`user_id` int(11) NOT NULL,
	`code` varchar(255) NOT NULL,
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT `uc_pw_user_id` UNIQUE (`user_id`),
	FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rel_player_user` (
	`player_id` int(11) NOT NULL,
	`user_id` int(11) NOT NULL,
	CONSTRAINT `rel_player_id` UNIQUE (`player_id`),
	CONSTRAINT `rel_user_id` UNIQUE (`user_id`),
	FOREIGN KEY (`player_id`) REFERENCES player(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `article` (
	`id` int(64) NOT NULL AUTO_INCREMENT, 
	`author_id` int(11) NOT NULL , 
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`article_type` ENUM('NEWS', 'MATCH', 'PAGE') NOT NULL,
	`header` varchar(64) NOT NULL , 
	`context` varchar(256) NOT NULL , 
	`content` text NOT NULL , 
	`image_url` VARCHAR(64) NOT NULL , 
	`published` tinyint(1) NOT NULL,
	CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
	PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `page` (
	`article_id` int(64) NOT NULL,
	`article_title` varchar(64) NOT NULL,
	`url_name` varchar(64) NOT NULL,
	`priority` int(8) NOT NULL,
	`published` tinyint(1) NOT NULL,
	CONSTRAINT `rel_article_id` UNIQUE (`article_id`),
	FOREIGN KEY (`article_id`) REFERENCES article(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `image` (
	`id` int(64) NOT NULL AUTO_INCREMENT, 
	`uploader_id` int(11) NOT NULL, 
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`image_type` ENUM('FRONT', 'FREE') NOT NULL,
	`title` varchar(64) NOT NULL , 
	`url` varchar(64) NOT NULL , 
	CONSTRAINT `fk_uploader_id` FOREIGN KEY (`uploader_id`) REFERENCES `user` (`id`),
	PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `match_report` (
	`match_id` int(11) NOT NULL, 
	`article_id` int(64) NOT NULL,
	`home_score` int(8) NOT NULL,
	`away_score` int(8) NOT NULL,
	`home_score_pause` int(8) NOT NULL,
	`away_score_pause` int(8) NOT NULL,
	`supporters` int(11),
	`published` tinyint(1) NOT NULL,
	CONSTRAINT `fk_report_match` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `fk_report_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bug` (
	`id` int(32) NOT NULL AUTO_INCREMENT, 
	`user_id` int(11) NOT NULL,
	`title` varchar(64) NOT NULL,
	`priority` int(8) NOT NULL,
	`os` varchar(64) NOT NULL,
	`browser` varchar(64) NOT NULL,
	`problem` text NOT NULL,
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`fixed` tinyint(1) NOT NULL DEFAULT 0,
	CONSTRAINT `fk_reporter_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
	PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `feature_request` (
	`id` int(32) NOT NULL AUTO_INCREMENT, 
	`user_id` int(11) NOT NULL,
	`title` varchar(64) NOT NULL,
	`description` text NOT NULL,
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`done` tinyint(1) NOT NULL DEFAULT 0,
	CONSTRAINT `fk_requester_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
	PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

