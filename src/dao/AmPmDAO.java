package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.AmPm;

public class AmPmDAO {

	/*午前/午後のどちらかを取得*/
	public static AmPm serchAmPm(int ampmID){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AmPm ampm = new AmPm();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fe?useSSL=false",
					"user1", "pass1");
			String sql = "select * from ampm where ampmid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ampmID);
			rs = pstmt.executeQuery();
			rs.next();
			int ampmid = rs.getInt("ampmid");
			String ampmName = rs.getString("ampmname");
			ampm.setAmpmID(ampmid);
			ampm.setAmpmName(ampmName);
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
		return ampm;
	}
}