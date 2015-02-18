package fremad.domain;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PlayerNoteObject")
public class PlayerNoteObject {

	private int id;
	private int playerId;
	private String note;
	private Timestamp date;
	
	public PlayerNoteObject(int id, int playerId, String note, Timestamp date) {
		super();
		this.id = id;
		this.playerId = playerId;
		this.note = note;
		this.date = date;
	}
	
	public PlayerNoteObject(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	
	
}
