package dto;

/*ログインの際の入力内容保持用*/
public class Login {

	private String userID;
	private String password;

	public Login(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public String getPassword() {
		return password;

	}
}
