package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Score;
import dto.Sort;

public class HistoryOfExamDAO {

	/*受験履歴追加*/
	public static void addHOE(Score score){
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "insert into historyofexam (userID, id, ampmid, score, time, sorf)value(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, score.getUserID());
			pstmt.setString(2, score.getId());
			pstmt.setInt(3, score.getAmpmID());
			pstmt.setInt(4, score.getScore());
			pstmt.setString(5, score.getDate());
			pstmt.setString(6, score.getsOrF());
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){

		}catch(SQLException e){

		}finally{
			try{
				if(pstmt != null){
					pstmt .close();
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

	/*指定されたソートをかけたリストを返す*/
	public static ArrayList<Score> serchAllHOE(Sort sort, String userID){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Score> list = new ArrayList<Score>();
		int flg = sort.getFlg();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1","pass1");
			String sql = "select * from historyofexam where userID = ?";
			if(flg - 64 >= 0){
				flg -= 64;
				sql += " order by time desc";
				if(flg - 32 >= 0){
					flg -= 32;
					sql += ", id desc";
					if(flg - 8 >= 0){
						flg -= 8;
						sql += ", score desc";
						if(flg - 2 >= 0){
							flg -= 2;
							sql += ", ampmid asc";
						}else {
							sql += ", ampmid desc";
						}
					}else if(flg - 4 >= 0){
						flg -= 4;
						sql += ", score asc";
						if(flg - 2 >= 0){
							flg -= 2;
							sql += ", ampmid asc";
						}else {
							sql += ", ampmid desc";
						}
					}
				}else if(flg - 16 >= 0){
					flg -= 16;
					sql += ", id asc";
					if(flg - 8 >= 0){
						flg -= 8;
						sql += ", score desc";
						if(flg - 2 >= 0){
							flg -= 2;
							sql += ", ampmid asc";
						}else {
							sql += ", ampmid desc";
						}
					}else if(flg - 4 >= 0){
						flg -= 4;
						sql += ", score asc";
						if(flg - 2 >= 0){
							flg -= 2;
							sql += ", ampmid asc";
						}else {
							sql += ", ampmid desc";
						}
					}
				}
			}else if(flg - 32 >= 0){
				flg -= 32;
				sql += " order by id desc";
				if(flg - 8 >= 0){
					flg -= 8;
					sql += ", score desc";
					if(flg - 2 >= 0){
						flg -= 2;
						sql += ", ampmid asc";
					}else {
						sql += ", ampmid desc";
					}
				}else if(flg - 4 >= 0){
					flg -= 4;
					sql += ", score asc";
					if(flg - 2 >= 0){
						flg -= 2;
						sql += ", ampmid asc";
					}else {
						sql += ", ampmid desc";
					}
				}
			}else if(flg - 16 >= 0){
				flg -= 16;
				sql += " order by id asc";
				if(flg - 8 >= 0){
					flg -= 8;
					sql += ", score desc";
					if(flg - 2 >= 0){
						flg -= 2;
						sql += ", ampmid asc";
					}else {
						sql += ", ampmid desc";
					}
				}else if(flg - 4 >= 0){
					flg -= 4;
					sql += ", score asc";
					if(flg - 2 >= 0){
						flg -= 2;
						sql += ", ampmid asc";
					}else {
						sql += ", ampmid desc";
					}
				}
			}else if(flg - 8 >= 0){
				flg -= 8;
				sql += " order by score desc";
				if(flg - 2 >= 0){
					flg -= 2;
					sql += ", ampmid asc";
				}else {
					sql += ", ampmid desc";
				}
			}else if(flg - 4 >= 0){
				flg -= 4;
				sql += " order by score asc";
				if(flg - 2 >= 0){
					flg -= 2;
					sql += ", ampmid asc";
				}else {
					sql += ", ampmid desc";
				}
			}else if(flg - 2 >= 0){
				flg -= 2;
				sql += " order by ampmid asc";
			}else{
				sql += " order by ampmid desc";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String d = rs.getString("time");
				String[] date = d.split("[\u002E]0");
				String id = rs.getString("id");
				int ampmID = rs.getInt("ampmid");
				int score = rs.getInt("score");
				String sOrF = rs.getString("sorf");
				list.add(new Score(id, ampmID, userID, score, date[0], sOrF, ""));
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

	/*解答　未か済を表示*/
	public static String serchHOE(String id, String userID, int ampmID, boolean flg){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String str = "未";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String conditions = " from historyofexam where id = ? and userID = ? and ampmID = ?";
			String sql = "select userID" + conditions;

			if(flg){
				sql += " and userID = (select userID" + conditions + ")";
			}

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, userID);
			pstmt.setInt(3, ampmID);

			if(flg){
				pstmt.setString(4, id);
				pstmt.setString(5, userID);
				pstmt.setInt(6, 1);
			}

			rs = pstmt.executeQuery();
			if(rs.next()){
				String user = rs.getString("userID");
				if(user != null){
					str = "済";
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
		return str;
	}

	/*全ユーザの合格率算出*/
	public static double serchSPercent(String id, int ampmID, boolean flg){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double per = 0.0;
		int sub = 0;
		int all = 0;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");

			String conditions = " from historyofexam where id = ? and ampmID = ? and sorf = coalesce(?, sorf)";
			String sql = "select count(distinct userID) as num" + conditions;

			if(flg){
				sql += " and userID in (select userID" + conditions + ")";
			}

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, ampmID);
			pstmt.setString(3, null);

			if(flg){
				pstmt.setString(4, id);
				pstmt.setInt(5, 1);
				pstmt.setString(6, null);
			}

			rs = pstmt.executeQuery();
			rs.next();
			all = rs.getInt("num");
			rs.close();

			pstmt.setString(3, "合格");

			if(flg){
				pstmt.setString(6, "合格");
			}

			rs = pstmt.executeQuery();
			rs.next();
			sub = rs.getInt("num");

			if(all != 0){
				per = (double)sub / all * 100;
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
		return per;
	}
}