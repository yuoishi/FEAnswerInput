package dto;

import java.io.Serializable;

/*午前午後の区分管理用*/
public class AmPm implements Serializable{
	private int ampmID;
	private String ampmName;

	public AmPm(){}

	public AmPm(int ampmID, String ampmName) {
		this.ampmID = ampmID;
		this.ampmName = ampmName;
	}

	public int getAmpmID() {
		return ampmID;
	}
	public void setAmpmID(int ampmID) {
		this.ampmID = ampmID;
	}

	public String getAmpmName() {
		return ampmName;
	}
	public void setAmpmName(String ampmName) {
		this.ampmName = ampmName;
	}
}