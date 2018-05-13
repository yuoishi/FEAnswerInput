package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Login;

public class LoginDAO {

	/*登録されたユーザIDとパスワードと一致するか確認*/
	public static Login serchUser(String userID, String password){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Login login = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String sql = "select userID, password from user where userID = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String id = rs.getString("userID");
				String pw = rs.getString("password");
				login = new Login(id, pw);
			}
		}catch(ClassNotFoundException e){

		}catch(SQLException e){

		}finally{
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){

			}
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException e){

			}
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){

			}
		}
		return login;
	}
}
