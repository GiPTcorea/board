package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import DTO.MemberDTO;

public class MemberDAO {
	/*
	 * public int insert(Connection conn , MemberDTO dto)
	 * 
	 * public int delete(Connection conn, MemberDTO dto)
	 * 
	 * public int update(Connection conn, MemberDTO dto)
	 * 
	 * public MemberDTO select(Connection conn, MemberDTO dto)
	 */
	
	private static MemberDAO dao = new MemberDAO();
	public static MemberDAO getInstance(){
		if(dao==null){
			dao=new MemberDAO();
		}
		return dao;
	}
	
	public int update(Connection conn, MemberDTO dto, int uid) {
		//
		int isSuccess = 0; // 0�̸� ����
		PreparedStatement pstmt = null;
		/*String sql = "update Member_ set encrypted_password=?,updated_at=?,nickname=?,phone=?,area=?,"
				+ "car=?,caryear=?,ton=?,age=?,year=?,mention=? where uid=?";
		 */
		String sql ="update users set nickname=?,phone=?,car=?,ton=?,age=?,name=? where uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, dto.getPassword());
			//pstmt.setString(2, dto.getUpdated_at());
			pstmt.setString(1, dto.getNickName());
			pstmt.setString(2, dto.getPhone());
			//pstmt.setString(5, dto.getArea());
			pstmt.setString(3, dto.getCar());
			//pstmt.setString(7, dto.getCarYear());
			pstmt.setString(4, dto.getTon());
			pstmt.setString(5, dto.getAge());
			pstmt.setString(6, dto.getName());
			//pstmt.setString(10, dto.getYear());
			//pstmt.setString(11, dto.getMention());
			
			pstmt.setInt(7,uid);
			isSuccess = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);

		}

		return isSuccess;
	}
	public int changeImg(Connection conn, int uid,String path){
		PreparedStatement pstmt = null;
		String sql = "update users set picturePath=? where uid=?";
		int isSuccess=-1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, path);
			pstmt.setInt(2, uid);
			isSuccess=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	public JSONObject select(Connection conn, int uid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//String sql = "select * from _Member where _ID=?";
		String sql = "select * from users where uid=?";
		JSONObject jsonObject = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,uid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				jsonObject = ResultSetTojsonObject(rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(rs);
			CloseSet.close(pstmt);
		}

		return jsonObject;
	}

	private JSONObject ResultSetTojsonObject(ResultSet rs) {
		JSONObject jsonObject =new JSONObject();
		
		//MemberDTO dto = new MemberDTO();
		try {
			jsonObject.put("uid",rs.getString("uid"));
			jsonObject.put("area",rs.getString("area"));
			jsonObject.put("car",rs.getString("car"));
			//jsonObject.put("caryear",rs.getString("caryear"));
			//jsonObject.put("createdAt",rs.getString("created_at"));
			jsonObject.put("picturePath", rs.getString("picturePath"));
			jsonObject.put("logid",rs.getString("logid"));
			//jsonObject.put("memtion",rs.getString("mention"));
			jsonObject.put("name", rs.getString("name"));
			jsonObject.put("nickname",rs.getString("nickname"));
			jsonObject.put("phone",rs.getString("phone"));
			jsonObject.put("ton",rs.getString("ton"));
			jsonObject.put("age",rs.getString("age"));
			jsonObject.put("year",rs.getString("year"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;

	}
}
