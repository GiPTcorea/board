package ContentService;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;
import DAO.MemberDAO;

public class SelectMyContent implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int ContentNumber=Integer.parseInt(request.getParameter("ContentNumber"));
		HttpSession session = request.getSession();
		int uid =(int) session.getAttribute("uid");
		ContentDAO contentDao=ContentDAO.getInstance();
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection conn=ConnectionProvider.getConnection();
		//JSONObject jsonObject=null;
		JSONArray jsonArray = new JSONArray();
		
		//jsonObject=memberDAO.select(conn, uid);
		
		if(ContentNumber<0)
		{
			 jsonArray=contentDao.selectMyAll(conn,uid,0);
		}else{
			 jsonArray=contentDao.selectMyAll(conn,uid,ContentNumber);
		}
		conn.close();
		PrintWriter out = response.getWriter();
		out.println(jsonArray);
		
		return null;
	}
}


