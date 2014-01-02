package CommentService;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.CommentDAO;
import DAO.ContentDAO;
import DAO.MemberDAO;
import DTO.CommentDTO;
import Util.DateUtil;

public class WriteComment implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int uid = (int) session.getAttribute("uid");
		int ContentNumber = Integer.parseInt(request
				.getParameter("contentNumber"));
		String comment = request.getParameter("comment");
		DateUtil dateUtil = new DateUtil();
		String createdAt = dateUtil.getDate(2);

		Connection conn = ConnectionProvider.getConnection();
		ContentDAO contentDAO = ContentDAO.getInstance();
		CommentDAO commentDAO = CommentDAO.getInstance();
		MemberDAO memberDAO = MemberDAO.getInstance();
		JSONObject jsonObject=null;
		
		jsonObject=memberDAO.select(conn, uid);
		CommentDTO dto = new CommentDTO();
		dto.setComment(comment);
		dto.setContentNumber(ContentNumber);
		dto.setCreatedAt(createdAt);
		dto.setID(uid);
		dto.setLogID(jsonObject.get("logid").toString());
		dto.setPicturePath(jsonObject.get("picturePath").toString());
		//System.out.println(jsonObject.get("picturePath").toString());
		jsonObject.clear();
		
		jsonObject = new JSONObject();
		jsonObject.put("isokay", false);
	
		try {
			conn.setAutoCommit(false); // Ʈ������ ����

			contentDAO.CntSet(conn, ContentNumber, "increaseCommentCnt");
			if (commentDAO.insert(conn, dto) != 0) {
				jsonObject.put("isokay", true);

			}
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
		}
		conn.close();
		PrintWriter out = response.getWriter();
		out.println(jsonObject);
		return null;
	}

}
