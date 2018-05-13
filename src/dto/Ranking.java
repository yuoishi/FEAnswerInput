package dto;

/*ランキング表示用*/
public class Ranking {
	private String userID;
	private int score;

	public Ranking(){}

	public Ranking(String userID, int score) {
		this.userID = userID;
		this.score = score;
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
}