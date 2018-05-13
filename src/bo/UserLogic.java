package bo;

import dao.UserDAO;
import dto.User;

public class UserLogic {

	/*パスワード再設定の時のユーザ確認処理*/
	public static boolean execute(User user){
		boolean flg = false;

		User u = UserDAO.serchUser(user);

		if(user != null && u.getUserID() != null){
			if(u.getUserID().equals(user.getUserID())){
				if(u.getEmail().equals(user.getEmail())){
					if(u.getSq().equals(user.getSq())){
						flg = true;
					}
				}
			}
		}
		return flg;
	}
}