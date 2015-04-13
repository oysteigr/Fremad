package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchReportObject")
public class MatchReportObject {
	int matchId; 
	int articleId;
	int homeScore;
	int awayScore;
	int homeScorePause;
	int awayScorePause;
	int supporters;
	boolean published;
	
	public MatchReportObject() {
		super();
	}
	
	public MatchReportObject(int matchId, int articleId, int homeScore,
			int awayScore, int homeScorePause, int awayScorePause,
			int supporters, boolean published) {
		super();
		this.matchId = matchId;
		this.articleId = articleId;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.homeScorePause = homeScorePause;
		this.awayScorePause = awayScorePause;
		this.supporters = supporters;
	}
	
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	public int getHomeScorePause() {
		return homeScorePause;
	}
	public void setHomeScorePause(int homeScorePause) {
		this.homeScorePause = homeScorePause;
	}
	public int getAwayScorePause() {
		return awayScorePause;
	}
	public void setAwayScorePause(int awayScorePause) {
		this.awayScorePause = awayScorePause;
	}
	public int getSupporters() {
		return supporters;
	}
	public void setSupporters(int supporters) {
		this.supporters = supporters;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	
}
