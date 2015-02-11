package fremad.dao;


import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;

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
	
}
