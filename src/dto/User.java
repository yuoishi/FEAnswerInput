package dto;

import java.io.Serializable;

/*ユーザ登録の内容保持用*/
public class User implements Serializable{
	private String userID;
	private String password;
	private String email;
	private String userName;
	private String name;
	private String phoneticName;
	private int birthday;
	private String sq;

	public User(){}

	public User(String userID, String password, String email, String userName, String name, String phoneticName, int birthday,
			String sq) {
		this.userID = userID;
		this.password = password;
		this.email = email;
		this.userName = userName;
		this.name = name;
		this.phoneticName = phoneticName;
		this.birthday = birthday;
		this.sq = sq;
	}

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneticName() {
		return phoneticName;
	}
	public void setPhoneticName(String phoneticName) {
		this.phoneticName = phoneticName;
	}
	public int getBirthday() {
		return birthday;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public String getSq() {
		return sq;
	}
	public void setSq(String sq) {
		this.sq = sq;
	}
}