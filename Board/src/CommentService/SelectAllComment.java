package CommentService;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.CommentDAO;

public class SelectAllComment implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		int ContentNumber=Integer.parseInt(request.getParameter("ContentNumber"));
		int CommentNumber=Integer.parseInt(request.getParameter("CommentNumber"));
		CommentDAO dao=CommentDAO.getInstance();
		Connection conn=ConnectionProvider.getConnection();

		JSONArray jsonArray = null;
		JSONObject jsonObject = new JSONObject();
		dao.getCount(conn, ContentNumber);
		if(CommentNumber<0)
		{
			 jsonArray=dao.selectAll(conn,ContentNumber,0);
		}else{
			 jsonArray=dao.selectAll(conn,ContentNumber,CommentNumber);
		}
		
		
		
		conn.close();
		PrintWriter out = response.getWriter();
		out.println( jsonArray);

		return null;
	}

}
