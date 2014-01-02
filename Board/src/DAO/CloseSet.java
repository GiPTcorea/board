package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseSet {
	/**
	 * <p>ResultSet��ü �Ҹ�.</p>
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
	 * <p>PreparedStatement��ü �Ҹ�.</p>
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
	 * <p>Statement��ü �Ҹ�.</p>
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

