package fremad.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.KeyValuePairObject;
import fremad.domain.PlayerNoteObject;
import fremad.domain.PlayerObject;
import fremad.domain.list.KeyValuePairListObject;
import fremad.domain.list.PlayerListObject;
import fremad.domain.list.PlayerNoteListObject;

@Repository
public class JdbcPlayerDao extends JdbcConnection implements PlayerDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcPlayerDao.class);
	
	public JdbcPlayerDao() {
		super();
	}

	@Override
	public PlayerListObject getPlayers() {
		LOG.debug("In getPlayers()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER;
		PlayerListObject players = new PlayerListObject();
		
		players.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(PlayerObject.class)));
		return players;
		
	}

	@Override
	public PlayerListObject getPlayersByTeam(final int teamId) {
		LOG.debug("In getPlayersByTeam(teamId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER + " where team = ?";
		PlayerListObject players = new PlayerListObject();
		
		players.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(java.sql.PreparedStatement ps)
						throws SQLException {
					ps.setInt(1, teamId);
				}
			}, new BeanPropertyRowMapper<>(PlayerObject.class)));
		
		return players;
		
	}
	
	@Override
	public PlayerObject addPlayer(PlayerObject playerObject) {
		LOG.debug("In addPlayer(playerObject)");

		SimpleJdbcInsert insertPlayer = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_PLAYER)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(playerObject);
		Number newId = insertPlayer.executeAndReturnKey(parameters);
		
		if(newId != null){
			playerObject.setId(newId.intValue());
			return playerObject;
		}
		
		return null;
	}

	@Override
	public PlayerObject updatePlayer(PlayerObject playerObject) {
		LOG.debug("In updatePlayer(playerObject)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER + " set "
				+ "first_name = :firstName, "
				+ "last_name = :lastName, "
				+ "number = :number, "
				+ "member_since = :memberSince, "
				+ "position = :position, "
				+ "preferred_foot = :preferredFoot, "
				+ "team = :team, "
				+ "active = :active "
				+ "where id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(playerObject);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return playerObject;

	}
	
	@Override
	public PlayerObject deletePlayer(PlayerObject playerObject) {
		LOG.debug("In deletePlayer(playerObject)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER + " where id = :playerId";
		SqlParameterSource parameters = new MapSqlParameterSource("playerId", playerObject.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
		
		return playerObject;
	}
	
	@Override
	public PlayerNoteListObject getPlayerNotes(){
		LOG.debug("In getPlayerNotes()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER_NOTE;
		PlayerNoteListObject playerNotes = new PlayerNoteListObject();
		
		playerNotes.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(PlayerNoteObject.class)));
		return playerNotes;
	}
	
	@Override
	public PlayerNoteObject addPlayerNote(PlayerNoteObject playerNoteObject) {
		LOG.debug("In addPlayerNote(playerNoteObject)");

		SimpleJdbcInsert insertPlayer = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_PLAYER_NOTE)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(playerNoteObject);
		Number newId = insertPlayer.executeAndReturnKey(parameters);
		
		if(newId != null){
			playerNoteObject.setId(newId.intValue());
			return playerNoteObject;
		}
		
		return null;
	}
	
	@Override
	public PlayerNoteObject deletePlayerNote(PlayerNoteObject playerNoteObject) {
		LOG.debug("In deletePlayerNote(playerNoteObject)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER_NOTE + " where id = :playerId";
		SqlParameterSource parameters = new MapSqlParameterSource("playerId", playerNoteObject.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
		
		return playerNoteObject;
	}
	
	
	@Override
	public KeyValuePairListObject<Integer, Integer> getPlayerUserRelations() {
		KeyValuePairListObject<Integer, Integer> keyValuePairListObject = 
				new KeyValuePairListObject<Integer, Integer>();
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER_USER_REL;
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
		for (Map<String, Object> row : rows) {
			KeyValuePairObject<Integer, Integer> keyValuePair = new KeyValuePairObject<Integer, Integer>();
			keyValuePair.setKey((Integer)row.get("player_id"));
			keyValuePair.setValue((Integer)row.get("user_id"));
			keyValuePairListObject.add(keyValuePair);
		}
		
		return keyValuePairListObject;
	}
	
	@Override
	public boolean addPlayerUserRelation(KeyValuePairObject<Integer, Integer> valuePair) {
		LOG.debug("In addPlayerUserRelation(valuePair)");

		SimpleJdbcInsert insertRelation = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_PLAYER_USER_REL);
		
		SqlParameterSource parameters = new MapSqlParameterSource("player_id", valuePair.getKey())
			.addValue("user_id", valuePair.getValue());
		
		return insertRelation.execute(parameters) > 0;
	}

	@Override
	public boolean deletePlayerUserRelation(KeyValuePairObject<Integer, Integer> valuePair) {
		LOG.debug("In deletePlayerUserRelation(valuePair)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER_USER_REL + " where player_id = :playerId AND user_id = :userId";
		SqlParameterSource parameters = new MapSqlParameterSource("playerId", valuePair.getKey())
			.addValue("userId", valuePair.getValue());
		
		return namedParameterJdbcTemplate.update(query, parameters) > 0;
	}

	
}
