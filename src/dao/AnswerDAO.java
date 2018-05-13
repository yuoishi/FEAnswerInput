package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Answer;
import dto.CorrectAnswer;

public class AnswerDAO {

	/*午前問題の正答を取得*/
	public static CorrectAnswer serchCorrectAnswer(Answer answer){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CorrectAnswer ca = new CorrectAnswer();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String id = answer.getId();
			int ampmID = answer.getAmpmID();
			String sql = "select answer from examansweram where id = ? and ampmid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, ampmID);
			rs = pstmt.executeQuery();
			rs.next();
			String correctAnswer = rs.getString("answer");
			ca.setAnswer(correctAnswer);
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
		return ca;
	}

	/*午後問題の正答を取得*/
	public static CorrectAnswer serchCorrectAnswer(Answer answer, int examNo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CorrectAnswer ca = new CorrectAnswer();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String id = answer.getId();
			int ampmID = answer.getAmpmID();
			String sql = "select exam" + examNo + " from examanswerpm where id = ? and ampmid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, ampmID);
			rs = pstmt.executeQuery();
			rs.next();
			String correctAnswer = rs.getString("exam" + examNo);
			ca.setAnswer(correctAnswer);
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
		return ca;
	}
}