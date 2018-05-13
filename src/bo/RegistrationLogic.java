package bo;

import dao.RegistrationDAO;
import dto.User;

public class RegistrationLogic {

	/*ユーザ登録処理呼び出し*/
	public static boolean execute(User user){
		int row = RegistrationDAO.insertUser(user);
		boolean flg = false;
		if(row > 0){
			flg = true;
		}
		return flg;
	}

	/*パスワード再設定処理呼び出し*/
	public static boolean execute(String userID, String pw){
		int row = RegistrationDAO.updatePassword(userID, pw);
		boolean flg = false;
		if(row > 0){
			flg = true;
		}
		return flg;
	}
}
