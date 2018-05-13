package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Exam;

public class ExamDAO {

	/*問題の年度取得 例 H29年秋 */
	public static Exam serchExam(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Exam exam = new Exam();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String sql = "select * from exam where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			String i = rs.getString("id");
			String examName = rs.getString("examname");
			exam.setId(i);
			exam.setExamName(examName);
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
		return exam;
	}

	/*問題年度全て取得*/
	public static ArrayList<Exam> serchAllExam(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Exam> list = new ArrayList<Exam>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "select * from exam order by id desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String id = rs.getString("id");
				String examName = rs.getString("examname");
				list.add(new Exam(id, examName));
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
