package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UserDAO {

	/*ユーザ名取得*/
	public static String[] serchUserName(String[] s){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "select username from user where userID = ?";
			pstmt = con.prepareStatement(sql);
			for(int i = 0; i < s.length; i++){
				pstmt.setString(1, s[i]);
				rs = pstmt.executeQuery();
				if(rs.next()){
					s[i] = rs.getString("username");
				}
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
		return s;
	}

	/*パスワード再設定の確認事項の登録値を取得*/
	public static User serchUser(User user){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = new User();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "select email, sq from user where userID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			rs = pstmt.executeQuery();
			if(rs.next()){
				String email = rs.getString("email");
				String sq = rs.getString("sq");
				u.setUserID(user.getUserID());
				u.setEmail(email);
				u.setSq(sq);
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
		return u;
	}
}