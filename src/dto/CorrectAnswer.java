package dto;

import java.io.Serializable;

/*正答保持用*/
public class CorrectAnswer implements Serializable{
	private String answer;

	public CorrectAnswer(){}

	public CorrectAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}