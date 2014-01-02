package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import DTO.ContentDTO;

public class ContentDAO {

	private static Map<String, String> SqlMap = new HashMap<String, String>();
	private static ContentDAO dao = new ContentDAO();

	public static ContentDAO getInstance() {
		if (dao == null) {
			dao = new ContentDAO();
		}
		SqlMap.put("increaseReadCnt",
				"update Content_ set _ReadCnt=_ReadCnt+1 where _Number=?");

		SqlMap.put("increaseMeTooCnt",
				"update Content_ set _MeTooCnt=_MeTooCnt+1 where _Number=?");

		SqlMap.put("increaseCommentCnt",
				"update Content_ set _CommentCnt=_CommentCnt+1 where _Number=?");

		SqlMap.put("decreaseMeTooCnt",
				"update Content_ set _MeTooCnt=_MeTooCnt-1 where _Number=?");

		SqlMap.put("decreaseCommentCnt",
				"update Content_ set _CommentCnt=_CommentCnt-1 where _Number=?");

		return dao;
	}

	public int isEmpty(Connection conn) {
		int isSuccess = 0;

		return isSuccess;
	}

	public int create(Connection conn) {
		int isSuccess = 0;
		PreparedStatement pstmt = null;
		String sql = "create table Content_("
				+ "_Number int not null primary key auto_increment,"
				+ "_ID varchar(10)," + "_Content text,"
				+ "picturePath varchar(30)," + "_ReadCnt int,"
				+ "_MeTooCnt int," + "_CommentCnt int,"
				+ "_CreatedAt datetime," + "_UpdatedAt datetime);";
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

	/**
	 * <p>
	 * �Խñ� ���.
	 * </p>
	 * 
	 * <pre>
	 * insert(Connection conn,contentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int insert(Connection conn, ContentDTO dto) {
		//
		int isSuccess = 0; // 0�̸� ����
		PreparedStatement pstmt = null;
		String sql = "insert into Content_(_ID,_Content,picturePath,_ReadCnt,"
				+ "_MeTooCnt,_CommentCnt,_CreatedAt,_logID) values(?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getID());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getPicturePath());
			pstmt.setInt(4, dto.getReadCnt());
			pstmt.setInt(5, dto.getMeTooCnt());
			pstmt.setInt(6, dto.getCommentCnt());
			pstmt.setString(7, dto.getCreatedAt());
			pstmt.setString(8, dto.getLogID());
			isSuccess = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);

		}

		return isSuccess;
	}

	/**
	 * <p>
	 * �ش� �Խñ� ����.
	 * </p>
	 * 
	 * <pre>
	 * upCreatedAt(Connection conn,contentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int update(Connection conn, ContentDTO dto) {
		//
		int isSuccess = 0; // 0�̸� ����
		PreparedStatement pstmt = null;
		String sql = "update Content_ set _Content=?,_UpdatedAt=? where _Number=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setString(2, dto.getCreatedAt());
			pstmt.setInt(3, dto.getNumber());

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
		String sql = "update Content_ set picturePath=? where _ID=?";
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

	/**
	 * <p>
	 * �ش� �Խñ� ����.
	 * </p>
	 * 
	 * <pre>
	 * delete(Connection conn,contentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int delete(Connection conn, int number) {
		//
		int isSuccess = 0; // 0�̸� ����
		PreparedStatement pstmt = null;
		String sql = "delete from Content_ where _Number=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, number);

			isSuccess = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);

		}

		return isSuccess;
	}

	/**
	 * <p>
	 * ��ȸ��,Like��,Hate�� ����.
	 * </p>
	 * 
	 * <pre>
	 * CntSet(Connection conn,contentDTO dto,String sqlType)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int CntSet(Connection conn, int number, String sqlType) {
		int isSuccess = 0;
		PreparedStatement pstmt = null;
		String sql = SqlMap.get(sqlType);

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			isSuccess = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);
		}

		return isSuccess;
		
	}

	/**
	 * <p>
	 * �Խñ� ��ü ��ȸ.
	 * </p>
	 * 
	 * <pre>
	 * selectAll(Connection conn,int start,int end)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public JSONArray selectAll(Connection conn, int start) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		String sql = "select * from Content_ order by _Number desc limit 10";
		if (start != 0) {
			sql = "select * from Content_ where _Number <? order by _Number desc limit 10";

		}
		try {
			pstmt = conn.prepareStatement(sql);
			if (start != 0) {
				pstmt.setInt(1, start);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {

				JSONObject obj = ResultSetTojsonObject(rs);
				array.add(obj);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(rs);
			CloseSet.close(pstmt);
			if (array.isEmpty()) {
				array = null;
			}
		}

		// return list;
		return array;
	}

	/*
	 * public List<ContentDTO> selectAll(Connection conn, int start, int count)
	 * {
	 * 
	 * PreparedStatement pstmt = null; ResultSet rs = null; int end = start +
	 * count - 1; List<ContentDTO> list = new ArrayList<ContentDTO>(); JSONArray
	 * array=new JSONArray(); String sql =
	 * "select * from Content_ order by _Number desc limit ? ,?"; try { pstmt =
	 * conn.prepareStatement(sql); pstmt.setInt(1, start); pstmt.setInt(2, end);
	 * rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { ContentDTO dto = ResultSetTocontentDTO(rs);
	 * list.add(dto);
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { CloseSet.close(rs);
	 * CloseSet.close(pstmt); if (list.isEmpty()) { list = null; } }
	 * 
	 * //return list; return array; }
	 */
	/**
	 * <p>
	 * ������ �Խñ� ��ȸ.
	 * </p>
	 * 
	 * <pre>
	 * select(Connection conn, contentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */

	public JSONObject select(Connection conn, int number) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from Content_ where _Number=?";
		JSONObject jsonObject = new JSONObject();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				jsonObject = ResultSetTojsonObject(rs);
			}else{
				jsonObject=null;
			}

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject=null;
		} finally {
			CloseSet.close(rs);
			CloseSet.close(pstmt);
		}

		return jsonObject;
	}

	/*
	 * public ContentDTO select(Connection conn, ContentDTO dto) {
	 * 
	 * PreparedStatement pstmt = null; ResultSet rs = null; String sql =
	 * "select * from _Content where _Number=?";
	 * 
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1,
	 * dto.getNumber());
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { // dto = ResultSetTocontentDTO(rs); }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { CloseSet.close(rs);
	 * CloseSet.close(pstmt); }
	 * 
	 * return dto; }
	 */
	/**
	 * <p>
	 * ���� �Խñ� ��ȸ.
	 * </p>
	 * 
	 * <pre>
	 * selectMyAll(Connection conn, ContentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public JSONArray selectMyAll(Connection conn, int ID, int start) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		JSONObject jsonObject = null;
		String sql = "select * from Content_ where _ID=?  order by _Number desc limit 10";

		if (start != 0) {
			sql = "select * from Content_ where _ID=? and _Number <? order by _Number desc limit 10";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID);
			if (start != 0) {
				pstmt.setInt(2, start);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {

				jsonObject = ResultSetTojsonObject(rs);
				array.add(jsonObject);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(rs);
			CloseSet.close(pstmt);
			if (array.isEmpty()) {
				array = null;
			}
		}

		return array;
	}

	private JSONObject ResultSetTojsonObject(ResultSet rs) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("content", rs.getString("_Content"));
			jsonObject.put("createdAt", rs.getString("_CreatedAt"));
			jsonObject.put("readCnt", rs.getInt("_ReadCnt"));
			jsonObject.put("number", rs.getInt("_Number"));
			jsonObject.put("logid", rs.getString("_logID"));
			jsonObject.put("id", rs.getInt("_ID"));
			jsonObject.put("commentCnt", rs.getInt("_CommentCnt"));
			jsonObject.put("metooCnt", rs.getInt("_MeTooCnt"));
			jsonObject.put("picturePath", rs.getString("picturePath"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;

	}
	/*
	 * private ContentDTO ResultSetTocontentDTO(ResultSet rs) { ContentDTO dto =
	 * new ContentDTO(); try { dto.setContent(rs.getString("_Content"));
	 * dto.setCreatedAt(rs.getString("_CreatedAt"));
	 * dto.setReadCnt(rs.getInt("_ReadCnt"));
	 * dto.setNumber(rs.getInt("_Number")); dto.setID(rs.getString("_ID"));
	 * dto.setCommentCnt(rs.getInt("_CommentCnt"));
	 * dto.setMeTooCnt(rs.getInt("_MeTooCnt"));
	 * dto.setPicturePath(rs.getString("_PicturePath"));
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return dto;
	 * 
	 * }
	 */
	public int count(Connection conn, int number)throws SQLException
	{
		String query=null;
		query="select _MeTooCnt from Content_ where _Number=?";
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		int count=0;
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, number);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getInt("_MeTooCnt");
			}else{
				count=-1;
			}

		}
		finally{
			if(rs!=null){rs.close();}
			if(pstmt!=null){rs.close();}
		}
			
		return count;
		
	}
}
