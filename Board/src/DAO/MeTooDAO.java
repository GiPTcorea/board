package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeTooDAO {

	private static MeTooDAO dao = new MeTooDAO();
	public static MeTooDAO getInstance(){
		if(dao==null){
			dao=new MeTooDAO();
		}
		return dao;
	}
	
	public int create(Connection conn) {
		int isSuccess = 0;
		PreparedStatement pstmt = null;
		String sql = "create table MeToo_("
				+ "_Number int not null primary key auto_increment,"
				+ "_ID varchar(10),"
				+ "_ContentNumber int"
				+ ")";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);
		}
		return isSuccess;

	}
	public int insert(Connection conn, int uid,int number){
		int isSuccess=0;
		String sql = "insert into MeToo_(_ID,_ContentNumber) values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, number);
			
			if(pstmt.executeUpdate()>0){
				isSuccess=1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);
		}
		
		return isSuccess;
		
	}
	public int select(Connection conn, int uid,int number){
		int isSuccess=0;
		String sql = "select * from MeToo_ where _ID=? and _ContentNumber=?";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, number);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				isSuccess=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);
			CloseSet.close(rs);
		}
		
		return isSuccess;
		
	}
}
