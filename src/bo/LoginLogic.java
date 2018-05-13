package bo;

import dao.LoginDAO;
import dto.Login;

public class LoginLogic {

	/*ログイン成功失敗判定*/
	public static boolean execute(String userID, String password){
		Login login = LoginDAO.serchUser(userID, password);
		boolean flg = false;
		if(login != null){
			flg = login.getUserID().equals(userID);
		}
		return flg;
	}
}
