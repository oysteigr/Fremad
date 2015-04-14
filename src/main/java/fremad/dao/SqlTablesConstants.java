package fremad.dao;

public interface SqlTablesConstants {
	
	/**
	 * This is references to tables and their variables
	 * 
	 */
	
	// Table names
	String SQL_TABLE_NAME_MATCH = "`match`";
	String SQL_TABLE_NAME_LEAGUE = "league";
	String SQL_TABLE_NAME_TEAM = "team";
	String SQL_TABLE_NAME_EVENT = "event";
	String SQL_TABLE_NAME_EVENT_TYPE = "event_type";
	String SQL_TABLE_NAME_PLAYER = "player";
	String SQL_TABLE_NAME_PLAYER_NOTE = "player_note";
	String SQL_TABLE_NAME_TABLE_ENTRY = "table_entry";
	String SQL_TABLE_NAME_USER = "user";
	String SQL_TABLE_NAME_USER_META = "user_meta";
	String SQL_TABLE_NAME_USER_ROLE_REQUEST = "user_role_request";	
	String SQL_TABLE_NAME_USER_LOGIN = "user_login";
	String SQL_TABLE_NAME_USER_VALIDATION = "user_validation";
	String SQL_TABLE_NAME_USER_FOGOT_PASSWORD = "user_forgot_password";
	String SQL_TABLE_NAME_PLAYER_USER_REL = "rel_player_user";
	String SQL_TABLE_NAME_ARTICLE = "article";
	String SQL_TABLE_NAME_IMAGE = "image";
	String SQL_TABLE_NAME_PAGE = "page";
	String SQL_TABLE_NAME_MATCH_REPORT = "match_report";
	String SQL_TABLE_NAME_BUG = "bug";
	String SQL_TABLE_NAME_FEATURE_REQUEST = "feature_request";
	
	// Table values
	
	String[] SQL_CREATE_TABLE_STRINGS = {
		"CREATE TABLE `team` ("
			+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
			+ " `name` varchar(255) DEFAULT NULL,"
			+ " `online_id` int(11) DEFAULT NULL,"
			+ "PRIMARY KEY (`id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `player` ("
			+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
			+ " `first_name` varchar(255) NOT NULL,"
			+ " `last_name` varchar(255) NOT NULL,"
			+ " `number` int(6) NOT NULL,"
			+ " `member_since` year(4) NOT NULL,"
			+ " `position` varchar(64) NOT NULL,"
			+ " `preferred_foot` enum('left', 'right'),"
			+ " `team` int(11) NOT NULL,"
			+ " `active` tinyint(1) NOT NULL DEFAULT 0,"
			+ " CONSTRAINT `fk_player_team` FOREIGN KEY (`team`) REFERENCES `team` (`id`),"
			+ " PRIMARY KEY (`id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `player_note` ("
			+ " `id` int(16) NOT NULL AUTO_INCREMENT,"
			+ " `player_id` int(11) NOT NULL,"
			+ " `field` varchar(255) NOT NULL,"
			+ " `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ " CONSTRAINT `fk_player_note` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),"
			+ " PRIMARY KEY (`id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;",
		"CREATE TABLE `league` ("
			+ " `id` int(11) NOT NULL,"
			+ " `name` varchar(255),"
			+ " `year` year(4) DEFAULT NULL,"
			+ " `team` int(11) DEFAULT NULL,"
			+ " CONSTRAINT `fk_league_team` FOREIGN KEY (`team`) REFERENCES `team` (`id`),"
			+ " PRIMARY KEY (`id`)"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `match` ("
			+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
			+ " `league` int(11) NOT NULL,"
			+ " `fremad_team` int(11) NOT NULL,"
			+ " `home_match` tinyint(1) NOT NULL,"
			+ " `fremad_goals` tinyint DEFAULT NULL,"
			+ " `opposing_team_name` varchar(255) NOT NULL,"
			+ " `opposing_team_id` int(11) NOT NULL,"
			+ " `opposing_team_goals` tinyint DEFAULT NULL,"
			+ " `date` timestamp NOT NULL,"
			+ " `field` varchar(255) NOT NULL,"
			+ " CONSTRAINT `fk_match_team` FOREIGN KEY (`team`) REFERENCES `team` (`id`),"
			+ " CONSTRAINT `fk_match_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`),"
			+ " CONSTRAINT `uc_foreign_team` UNIQUE (`date`, `opposing_team_id`),"
			+ " PRIMARY KEY (`id`)"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `event_type` ("
			+ " `id` int(4) NOT NULL,"
			+ " `name` varchar(255) NOT NULL,"
			+ " PRIMARY KEY (`id`)"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `event` ("
			+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
			+ " `type` int(4) NOT NULL,"
			+ " `match` int(11) NOT NULL,"
			+ " `time` tinyint NOT NULL,"
			+ " CONSTRAINT `fk_event_event_type` FOREIGN KEY (`type`) REFERENCES `event_type` (`id`),"
			+ " CONSTRAINT `fk_event_match` FOREIGN KEY (`match`) REFERENCES `match` (`id`),"
			+ " PRIMARY KEY (`id`)"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `table_entry` ("
			+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
			+ " `league_id` int(11) NOT NULL,"
			+ " `pos` tinyint NOT NULL,"
			+ " `team_name` varchar(255) NOT NULL,"
			+ " `team_id` int(11) NOT NULL,"
			+ " `match_count` tinyint NOT NULL,"
			+ " `goals_scored` int(8) NOT NULL,"
			+ " `goals_conceded` int(8) NOT NULL,"
			+ " `points` int(8) NOT NULL,"
			+ " `games_won` int(6) NOT NULL,"
			+ " `games_tied` int(6) NOT NULL,"
			+ " `games_lost` int(6) NOT NULL,"
			+ " CONSTRAINT `fk_table_entry_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`),"
			+ " CONSTRAINT `uc_league_team` UNIQUE (`league`, `team_id`),"
			+ " PRIMARY KEY (`id`)"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `user` ("
			+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
			+ " `user_name` varchar(255) NOT NULL,"
			+ " `password` varchar(255) NOT NULL,"
			+ " `salt` varchar(255) NOT NULL,"
			+ " `role` ENUM('SUPPORTER', 'PLAYER', 'AUTHOR', 'EDITOR', 'ADMIN', 'SUPER') NOT NULL DEFAULT 'SUPPORTER',"
			+ " `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ " `validated` tinyint(1) NOT NULL DEFAULT 0,"
			+ " CONSTRAINT `uc_user_name` UNIQUE (`user_name`),"
			+ " PRIMARY KEY (`id`)"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `user_role_request` ("
			+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
			+ " `user_id` int(11) NOT NULL,"
			+ " `requested_role` ENUM('SUPPORTER', 'PLAYER', 'AUTHOR', 'EDITOR', 'ADMIN', 'SUPER') NOT NULL,"
			+ " `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ " `accepted` tinyint(1) NOT NULL DEFAULT 0,"
			+ " `accepted_by` int(11) NOT NULL DEFAULT -1,"
			+ " CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),"
			+ " PRIMARY KEY (`id`)"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `user_login` ("
			+ "`id` int(16) NOT NULL AUTO_INCREMENT,"
			+ "`user_id` int(11) NOT NULL,"
			+ "`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ "CONSTRAINT `fk_login_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),"
			+ "PRIMARY KEY (`id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `user_meta` ("
			+ " `user_id` INT(64) NOT NULL , "
			+ " `first_name` VARCHAR(32) NOT NULL , "
			+ " `last_name` VARCHAR(32) NOT NULL , "
			+ " `phone_number` VARCHAR(16) , "
			+ " `birthday` DATE DEFAULT '0001-01-01', "
			+ " `home_town` VARCHAR(32) , "
			+ " `profession` VARCHAR(32) , "
			+ " CONSTRAINT `uc_user_id` UNIQUE (`user_id`),"
			+ " FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),"
			+ "PRIMARY KEY ( `user_id` )"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8",
		"CREATE TABLE `user_validation` ("
			+ " `user_id` int(11) NOT NULL,"
			+ " `code` varchar(255) NOT NULL,"
			+ " `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ " CONSTRAINT `uc_pw_user_id` UNIQUE (`user_id`),"
			+ " FOREIGN KEY (`user_id`) REFERENCES user(`id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;",
		"CREATE TABLE `user_forgot_password` ("
			+ " `user_id` int(11) NOT NULL,"
			+ " `code` varchar(255) NOT NULL,"
			+ " `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ " CONSTRAINT `uc_pw_user_id` UNIQUE (`user_id`),"
			+ " FOREIGN KEY (`user_id`) REFERENCES user(`id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;",
		"CREATE TABLE `rel_player_user` ("
			+ " `player_id` int(11) NOT NULL,"
			+ " `user_id` int(11) NOT NULL,"
			+ " CONSTRAINT `rel_player_id` UNIQUE (`player_id`),"
			+ " CONSTRAINT `rel_user_id` UNIQUE (`user_id`),"
			+ " FOREIGN KEY (`player_id`) REFERENCES player(`id`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ " FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ " ) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
		"CREATE TABLE `article` ("
			+ " `id` INT(64) NOT NULL AUTO_INCREMENT, "
			+ " `author_id` INT(64) NOT NULL , "
			+ " `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ " `type` VARCHAR(32) NOT NULL , "
			+ " `header` VARCHAR(64) NOT NULL , "
			+ " `context` VARCHAR(256) NOT NULL , "
			+ " `content` TEXT NOT NULL , "
			+ " `image_url` VARCHAR(64) NOT NULL , "
			+ " `published` tinyint(1) NOT NULL,"
			+ " CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),"
			+ "PRIMARY KEY ( `id` )"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
	};
	

	String[] SQL_DROP_TABLE_STRINGS = {
		"DROP TABLE `article`",
		"DROP TABLE `rel_player_user`",
		"DROP TABLE `user_forgot_password`",
		"DROP TABLE `user_validation`",
		"DROP TABLE `user_meta`",
		"DROP TABLE `user_login`",
		"DROP TABLE `user_role_request`",
		"DROP TABLE `user`",
		"DROP TABLE `table_entry`",
		"DROP TABLE `player_note`",
		"DROP TABLE `player`",
		"DROP TABLE `event`",
		"DROP TABLE `event_type`",
		"DROP TABLE `match`",
		"DROP TABLE `league`",
		"DROP TABLE `team`"
	};
}

