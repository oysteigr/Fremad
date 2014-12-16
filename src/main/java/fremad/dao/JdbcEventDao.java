package fremad.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.EventObject;
import fremad.domain.list.EventListObject;
import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcEventDao extends JdbcConnection implements EventDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcEventDao.class);
	
	public JdbcEventDao() {
		super();
	}
	
	@Override
	public EventListObject getEvents(int matchId) {
		EventListObject events = new EventListObject();
		
		try {
			this.prpstm = this.conn.prepareStatement("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_EVENT + " WHERE match = ?");
			this.prpstm.setInt(1, matchId);
			ResultSet res = this.prpstm.executeQuery();
			while (res.next()) {
				events.add(new EventObject());
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
		return events;
	}
	
	@Override
	public EventObject addEvent(EventObject event) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_EVENT + " "
				+ "(player, type, time, match) "
				+ "VALUES (?, ?, ?, ?)";
		LOG.debug("In addLeague with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);

			prpstm.setInt(1, event.getPlayer());
			prpstm.setInt(2, event.getEventType());
			prpstm.setInt(3, event.getMatchTime());
			prpstm.setInt(3, event.getMatchId());
			
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
		}

		return event;
	}
	
	@Override
	public EventObject updateEvent(EventObject event) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_EVENT + " SET "
				+ " player = ?, "
				+ " type = ?, "
				+ " time = ? "
				+ " match = ? "
				+ " WHERE id = ?";
		
		LOG.debug("In updateEvent with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);

			prpstm.setInt(1, event.getPlayer());
			prpstm.setInt(2, event.getEventType());
			prpstm.setInt(3, event.getMatchTime());
			prpstm.setInt(4, event.getMatchId());
			prpstm.setInt(5, event.getId());
			
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		
		return event;
	}
	
	@Override
	public EventObject deleteEvent(EventObject event) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_EVENT + " WHERE "
				+ " id = ?";
		
		LOG.debug("In addLeague with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, event.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		return event;
	}
	
}
