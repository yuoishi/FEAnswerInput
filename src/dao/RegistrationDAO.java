package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User;

public class RegistrationDAO {

	/*ユーザ登録処理*/
	public static int insertUser(User user){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String sql = "insert into user value(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserName());
			pstmt.setString(5, user.getName());
			pstmt.setString(6, user.getPhoneticName());
			pstmt.setInt(7, user.getBirthday());
			pstmt.setString(8, user.getSq());
			row = pstmt.executeUpdate();
		}catch(ClassNotFoundException e){

		}catch(SQLException e){

		}finally{
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
		return row;
	}

	/*パスワード更新処理*/
	public static int updatePassword(String userID, String pw){
		Connection con = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String sql = "update user set password = ? where userID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, userID);
			row = pstmt.executeUpdate();
		}catch(ClassNotFoundException e){

		}catch(SQLException e){

		}finally{
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
		return row;
	}
}