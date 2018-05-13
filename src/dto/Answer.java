package dto;

import java.io.Serializable;

/*解答内容保持用*/
public class Answer implements Serializable{
	private String id;
	private int ampmID;
	private String answer;

	public Answer(){}

	public Answer(String id, int ampmID, String answer) {
		this.id = id;
		this.ampmID = ampmID;
		this.answer = answer;
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

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}