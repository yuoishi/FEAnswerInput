package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamPmDAO {

	/*午後問題の設問名取得 例 1a,b,2c */
	public static ArrayList<String> serchExam(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String sql = "select * from exampm where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			for(int i = 1; i <= 13; i++){
				list.add(rs.getString("exam" + i));
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
		return list;
	}

	/*解答欄の選択肢の個数を取得*/
	public static ArrayList<String> serchAnswer(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String sql = "select * from answerpm where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			for(int i = 1; i <= 13; i++){
				list.add(rs.getString("exam" + i));
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
		return list;
	}
}