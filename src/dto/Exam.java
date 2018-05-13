package dto;

import java.io.Serializable;

/*問題年度管理用*/
public class Exam implements Serializable{
	private String id;
	private String examName;

	public Exam(){}

	public Exam(String id, String examName) {
		this.id = id;
		this.examName = examName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
}