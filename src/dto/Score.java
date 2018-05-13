package dto;

import java.io.Serializable;

/*得点や日付合否や詳細な○×Nを保持用*/
public class Score implements Serializable{
	private String id;
	private int ampmID;
	private String userID;
	private int score;
	private String date;
	private String sOrF;
	private String tOrF;

	public Score(){}

	public Score(String id, int ampmID, String userID, int score, String date, String sOrF, String tOrF) {
		this.id = id;
		this.ampmID = ampmID;
		this.userID = userID;
		this.score = score;
		this.date = date;
		this.sOrF = sOrF;
		this.tOrF = tOrF;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getAmpmID() {
		return ampmID;
	}

	public void setAmpmID(int ampmID) {
		this.ampmID = ampmID;
	}

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}

	public String getsOrF() {
		return sOrF;
	}
	public void setsOrF(String sOrF) {
		this.sOrF = sOrF;
	}

	public String gettOrF() {
		return tOrF;
	}
	public void settOrF(String tOrF) {
		this.tOrF = tOrF;
	}
}