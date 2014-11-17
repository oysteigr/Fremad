package fremad.dao;

public interface SqlTablesConstants {
	
	/**
	 * This is references to tables and their variables
	 * 
	 */
	
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	String DB_URL = "jdbc:mysql://localhost:3306/fremad";

	// Database credentials
	String USER = "famagusta";
	String PASS = "fremad123";
	
	// Table names
	
	String SQL_TABLE_NAME_ARTICLE = "articles";

	String SQL_TABLE_NAME_MATCH = "`match`";
	String SQL_TABLE_NAME_LEAGUE = "league";
	String SQL_TABLE_NAME_TEAM = "team";
	String SQL_TABLE_NAME_EVENT = "event";
	String SQL_TABLE_NAME_EVENT_TYPE = "event_type";
	String SQL_TABLE_NAME_PLAYER = "player";
	String SQL_TABLE_NAME_TABLE_ENTRY = "table_entry";
	
	
	// Table values
	
	String SQL_TABLE_VARIABLES_ARTICLE = ""
		    + "ArticleId INT(64) NOT NULL AUTO_INCREMENT, "
		    + "AuthorId INT(64), "
		    + "ArticleDate TIMESTAMP, "
		    + "ArticleType VARCHAR(32), "
		    + "ArticleHeader VARCHAR(64), "
		    + "ArticleContext VARCHAR(256), "
		    + "ArticleContent TEXT, "
		    + "ArticleImageUrl VARCHAR(64), "
		    + "PRIMARY KEY ( ArticleId )";

    String[] SQL_CREATE_TABLE_STRINGS = {
        "CREATE TABLE `team` ("
            + " `id` int(11) NOT NULL AUTO_INCREMENT,"
            + " `name` varchar(255) DEFAULT NULL,"
            + " `online_id` int(11) DEFAULT NULL,"
            + "PRIMARY KEY (`id`)"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8",
        "CREATE TABLE `player` ("
            + " `id` int(11) NOT NULL,"
            + " `first_name` varchar(255) NOT NULL,"
            + " `last_name` varchar(255) NOT NULL,"
            + " `member_since` year(4) NOT NULL,"
            + " `position` varchar(64) NOT NULL,"
            + " `preferred_foot` enum('left', 'right'),"
            + " `team` int(11) NOT NULL,"
            + " CONSTRAINT `fk_player_team` FOREIGN KEY (`team`) REFERENCES `team` (`id`)"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8",
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
            + " `team` int(11) NOT NULL,"
            + " `home_match` tinyint(1) NOT NULL,"
            + " `home_goals` tinyint DEFAULT NULL,"
            + " `opposing_team_name` varchar(255) NOT NULL,"
            + " `opposing_team_id` int(11) NOT NULL,"
            + " `opposing_team_goals` tinyint DEFAULT NULL,"
            + " `date` timestamp NOT NULL,"
            + " `field` varchar(255) NOT NULL,"
            + " CONSTRAINT `fk_match_team` FOREIGN KEY (`team`) REFERENCES `team` (`id`),"
            + " CONSTRAINT `fk_match_league` FOREIGN KEY (`league`) REFERENCES `league` (`id`),"
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
            + " `league` int(11) NOT NULL,"
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
            + " PRIMARY KEY (`id`)"
            + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8"
    };

    String[] SQL_DROP_TABLE_STRINGS = {
        "DROP TABLE `table_entry`",
        "DROP TABLE `player`",
        "DROP TABLE `event`",
        "DROP TABLE `event_type`",
        "DROP TABLE `match`",
        "DROP TABLE `league`",
        "DROP TABLE `team`"
    };
}

