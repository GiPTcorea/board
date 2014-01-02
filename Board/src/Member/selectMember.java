package Member;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.MemberDAO;

public class selectMember implements ProcessHandler  {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		int uid = (int) session.getAttribute("uid");
		Connection conn=ConnectionProvider.getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		JSONObject jsonObject;
		
		jsonObject=memberDAO.select(conn, uid);
		
		PrintWriter out = response.getWriter();
		out.println(jsonObject);
		conn.close();
		return null;
	}

}
