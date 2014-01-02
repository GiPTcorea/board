package ContentService;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;
import DAO.MemberDAO;
import DTO.ContentDTO;
import Util.DateUtil;

public class WriteContent implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String nextView="/Board/Board.jsp";
		DateUtil dateUtil = new DateUtil();
		HttpSession session=request.getSession();
		
		int uid=(int)session.getAttribute("uid");
		String content=request.getParameter("content");
		String createdAt = dateUtil.getDate(2);
		ContentDTO contentDto = new ContentDTO();
		Connection conn = ConnectionProvider.getConnection();
		JSONObject jsonObject=null;
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		jsonObject=memberDAO.select(conn, uid);
		
		contentDto.setID(uid);
		contentDto.setContent(content);
		contentDto.setLogID(jsonObject.get("logid").toString());
		contentDto.setPicturePath(jsonObject.get("picturePath").toString());
		contentDto.setCreatedAt(createdAt);
		contentDto.setMeTooCnt(0);
		contentDto.setReadCnt(0);
		contentDto.setCommentCnt(0);
		conn.close();
		
		conn = ConnectionProvider.getConnection();
		
		ContentDAO dao =ContentDAO.getInstance();
		
		if(dao.insert(conn,contentDto)!=1){
			//����
			nextView="/err.jsp";//���� ���� ��
		}
		conn.close();
		return nextView;
	}

}
