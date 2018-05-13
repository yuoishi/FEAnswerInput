package dto;

/*前回の得点保持用*/
public class PreScore {
	private int score;
	private boolean flg;

	public PreScore(){}

	public PreScore(int score, boolean flg) {
		this.score = score;
		this.flg = flg;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public boolean isFlg() {
		return flg;
	}
	public void setFlg(boolean flg) {
		this.flg = flg;
	}
}