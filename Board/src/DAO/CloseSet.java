package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseSet {
	/**
	 * <p>ResultSet按眉 家戈.</p>
	 * 
	 * <pre>
	 * Util.CloseSet.close(ResultSet rs)
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static void close(ResultSet rs){
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * <p>PreparedStatement按眉 家戈.</p>
	 * 
	 * <pre>
	 * Util.CloseSet.close(PreparedStatement pstmt)
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static void close(PreparedStatement pstmt){
		
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	/**
	 * <p>Statement按眉 家戈.</p>
	 * 
	 * <pre>
	 * Util.CloseSet.close(Statement stmt)
	 * </pre>
	 * 
	 * @param 
	 * @return 
	 */
	public static void close(Statement stmt){
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

