package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Exam;
import dto.PreScore;
import dto.Ranking;
import dto.Score;

public class ScoreDAO {

	/*前回の得点を取得*/
	public static PreScore serchScore(Score score){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreScore preScore = new PreScore();
		String id = score.getId();
		String userID = score.getUserID();
		int ampmID = score.getAmpmID();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "select amscore, pmscore from score where id = ? and userID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			String str = HistoryOfExamDAO.serchHOE(id, userID, ampmID, false);
			if(rs.next()){
				if(ampmID == 0 && str.equals("済")){
					preScore.setScore(rs.getInt("amscore"));
				}else if(ampmID == 1 && str.equals("済")){
					preScore.setScore(rs.getInt("pmscore"));
				}
				preScore.setFlg(true);
			}else{
				preScore.setFlg(false);
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
		return preScore;
	}

	/*今回の得点に更新*/
	public static void updateScore(Score score){
		Connection con = null;
		PreparedStatement pstmt = null;
		String id = score.getId();
		String userID = score.getUserID();
		int ampmID = score.getAmpmID();
		int scr = score.getScore();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "update score set ";
			if(ampmID == 0){
				sql += "amscore = ? ";
			}else{
				sql += "pmscore = ? ";
			}
			sql += "where id = ? and userID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scr);
			pstmt.setString(2, id);
			pstmt.setString(3, userID);
			pstmt.executeUpdate();
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
	}

	/*scoreテーブルに得点を追加*/
	public static void addScore(Score score){
		Connection con = null;
		PreparedStatement pstmt = null;
		String id = score.getId();
		String userID = score.getUserID();
		int ampmID = score.getAmpmID();
		int scr = score.getScore();
		Exam exam = ExamDAO.serchExam(id);
		String examName = exam.getExamName();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "insert into score (id, userID, examname, amscore, pmscore) value(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, userID);
			pstmt.setString(3, examName);
			if(ampmID == 0){
				pstmt.setInt(4, scr);
				pstmt.setInt(5, 0);
			}else{
				pstmt.setInt(4, 0);
				pstmt.setInt(5, scr);
			}
			pstmt.executeUpdate();
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
	}

	/*ランキング用に得点の上位10人を取得*/
	public static ArrayList<Ranking> serchScore(String id, int idx){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Ranking> list = new ArrayList<Ranking>();
		String[] str = {"totalscore", "amscore", "pmscore"};

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "select userID, " + str[idx] + " from score where id = ? order by " + str[idx] + " desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			int cnt = 0;
			String userID = "";
			int score = 0;
			while(rs.next() && cnt < 10){
				userID = rs.getString("userID");
				score = rs.getInt(str[idx]);
				list.add(new Ranking(userID, score));
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