package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import DTO.CommentDTO;
import DTO.ContentDTO;

public class CommentDAO {

	private static CommentDAO dao = new CommentDAO();

	public static CommentDAO getInstance() {
		if (dao == null) {
			dao = new CommentDAO();
		}
		return dao;
	}

	/*
	 * public int insert(Connection conn,CommentDTO dto) dto�� ������ _Content
	 * table�� ����, Number���� ���� Auto_increment�ϱ� ��� ���� X ��ȯ������ ����
	 * ���� ���θ� �׸��� PreparedStatement�� �ۼ����ֽð� �������� CloseSet���ִ�
	 * close()�Լ��� �Ҹ����ּ���
	 * 
	 * public int delete(Connection conn, CommentDTO dto) �ش� ��ȣ�� query������
	 * �ش� �� �����ǿ�
	 * 
	 * public int update(Connection conn, CommentDTO dto) insert�� ����ؿ�
	 * 
	 * public List<contentDTO> selectAll(Connection conn,int ContentNumber)
	 * ContentNumber�� �ش��ϴ� Comment���� ResultSet�� �����Ŀ� �Ʒ����ִ� �Լ���
	 * List�� ��ȯ�ϰ� �װ� ��ȯ��Ű�� �ǿ�
	 * 
	 * 
	 * private List<contentDTO> ResultSetToList(ResultSet rs) ResultSet�� List��
	 * �ٲٴ� �Լ�
	 */

	public int create(Connection conn) {
		int isSuccess = 0;
		PreparedStatement pstmt = null;
		String sql = "create table Comment_("
				+ "_Number int not null primary key auto_increment,"
				+ "_ContentNumber int," + "_ID varchar(10)," + "_Comment text,"
				+ "_CreatedAt datetime," + "_UpCreatedAt datetime);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;

	}
	public int changeImg(Connection conn, int uid,String path){
		PreparedStatement pstmt = null;
		String sql = "update Comment_ set picturePath=? where _ID=?";
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
	 * ��� ���.
	 * </p>
	 * 
	 * <pre>
	 * insert(Connection conn,CommentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int insert(Connection conn, CommentDTO dto) {
		//
		int isSuccess = 0; // 0�̸� ����
		PreparedStatement pstmt = null;
		String sql = "insert into Comment_(_ID,_Comment,_CreatedAt,_ContentNumber,picturePath,_logID) values(?,?,?,?,?,?)";
		System.out.println("사진경로 "+dto.getPicturePath());
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getID());
			pstmt.setString(2, dto.getComment());
			pstmt.setString(3, dto.getCreatedAt());
			pstmt.setInt(4, dto.getContentNumber());
			pstmt.setString(5, dto.getPicturePath());
			pstmt.setString(6, dto.getLogID());
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
	 * �ش� ��� ����.
	 * </p>
	 * 
	 * <pre>
	 * update(Connection conn,CommnetDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int update(Connection conn, CommentDTO dto) {
		//
		int isSuccess = 0; // 0�̸� ����
		PreparedStatement pstmt = null;
		String sql = "update Comment_ set _Comment=?,_Date=? where _Number=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
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

	/**
	 * <p>
	 * �ش� �Խñ� ����.
	 * </p>
	 * 
	 * <pre>
	 * delete(Connection conn,CommentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int delete(Connection conn, CommentDTO dto) {
		//
		int isSuccess = 0; // 0�̸� ����
		PreparedStatement pstmt = null;
		String sql = "delete from Comment_ where _Number=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getNumber());

			isSuccess = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			CloseSet.close(pstmt);

		}

		return isSuccess;
	}

	public int getCount(Connection conn, int number) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from Comment_ where _ContentNumber =?";
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();

			rs.next();
			count = rs.getInt("count(*)");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(rs);
			CloseSet.close(pstmt);
		}

		return count;
	}

	/**
	 * <p>
	 * �ش� �ۿ� �ִ� ��� ��ȸ.
	 * </p>
	 * 
	 * <pre>
	 * selectAll(Connection conn, CommentDTO dto)
	 * 
	 * </pre>
	 * 
	 * @param
	 * @param
	 * @return
	 */
	/*
	 * public List<CommentDTO> selectAll(Connection conn, int start) {
	 * 
	 * PreparedStatement pstmt = null; ResultSet rs = null; List<CommentDTO>
	 * list = new ArrayList<CommentDTO>(); String sql =
	 * "select * from Comment_ order by _Number desc limit 10"; if(start!=0) {
	 * sql
	 * ="select * from Comment_ where _Number <? order by _Number desc limit 10"
	 * ;
	 * 
	 * } try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1, start); rs =
	 * pstmt.executeQuery();
	 * 
	 * while (rs.next()) { CommentDTO dto = ResultSetTocommentDTO(rs);
	 * list.add(dto);
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { CloseSet.close(rs);
	 * CloseSet.close(pstmt); }
	 * 
	 * return list; }
	 */
	public int hasMore(Connection conn, int ContentNumber, int lastNumber) {
		System.out.println("lastNumber = " + lastNumber);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from Comment_ where _ContentNumber=? and _Number <?";
		int more = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ContentNumber);
			pstmt.setInt(2, lastNumber);
			rs = pstmt.executeQuery();
			System.out.println(1);
			if (rs.next()) {
				System.out.println(2);
				more = rs.getInt("count(*)");
				System.out.println("more=" + more);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(rs);
			CloseSet.close(pstmt);
		}

		return more;
	}

	public JSONArray selectNewAll(Connection conn, int ContentNumber, int latest) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		JSONObject jsonObject = null;
		String sql = "select * from Comment_ where _ContentNumber=? and _Number >? order by _Number";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ContentNumber);
			pstmt.setInt(2, latest);
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

	public JSONArray selectAll(Connection conn, int ContentNumber, int start) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		JSONObject jsonObject = null;
		int more=0;
		String sql = "select * from Comment_ where _ContentNumber=?  order by _Number desc limit 10";
		try {
			if (start != 0) {
				sql = "select * from Comment_ where _ContentNumber=? and _Number <? order by _Number desc limit 10";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ContentNumber);
			if (start != 0) {
				pstmt.setInt(2, start);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {

				jsonObject = ResultSetTojsonObject(rs);
				array.add(jsonObject);

			}
			more=(int) jsonObject.get("number");
			
			jsonObject = new JSONObject();
			
			if (array.size() != 10) {
				
				jsonObject.put("hasMore", 0);
			} else {
				jsonObject.put("hasMore", dao.hasMore(conn, ContentNumber, more));
			}
			array.add(jsonObject);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseSet.close(rs);
			CloseSet.close(pstmt);
		}

		return array;
	}

	/*
	 * public JSONArray selectAll(Connection conn, int start,) {
	 * 
	 * PreparedStatement pstmt = null; ResultSet rs = null; List<ContentDTO>
	 * list = new ArrayList<ContentDTO>(); JSONArray array=new JSONArray();
	 * String sql = "select * from Comment_ order by _Number desc limit 10";
	 * if(start!=0) {
	 * sql="select * from Comment_ where _Number <? order by _Number desc limit 10"
	 * ;
	 * 
	 * } try { pstmt = conn.prepareStatement(sql); if(start!=0){ pstmt.setInt(1,
	 * start); } rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) {
	 * 
	 * JSONObject obj = ResultSetTojsonObject(rs); array.add(obj); }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { CloseSet.close(rs);
	 * CloseSet.close(pstmt); if (array.isEmpty()) { array = null; } }
	 * 
	 * //return list; return array; }
	 *//*
		 * private CommentDTO ResultSetTocommentDTO(ResultSet rs) { CommentDTO
		 * dto = new CommentDTO(); try {
		 * dto.setComment(rs.getString("_Comment"));
		 * dto.setDate(rs.getString("_Date"));
		 * dto.setNumber(rs.getInt("_Number")); dto.setID(rs.getString("_ID"));
		 * dto.setContentNumber(rs.getInt("_ContentNumber"));
		 * 
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * return dto;
		 * 
		 * }
		 */
	private JSONObject ResultSetTojsonObject(ResultSet rs) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("comment", rs.getString("_Comment"));
			jsonObject.put("createdAt", rs.getString("_CreatedAt"));
			jsonObject.put("number", rs.getInt("_Number"));
			jsonObject.put("logid", rs.getString("_logID"));
			jsonObject.put("id", rs.getInt("_ID"));
			jsonObject.put("picturePath", rs.getString("picturePath"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;

	}
}
