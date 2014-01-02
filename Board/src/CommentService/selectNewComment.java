package CommentService;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.CommentDAO;

public class selectNewComment implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		int ContentNumber=Integer.parseInt(request.getParameter("contentNumber"));
		
		int CommentLatestNumber=Integer.parseInt(request.getParameter("commentLatestNumber"));
		
		CommentDAO dao=CommentDAO.getInstance();
		Connection conn=ConnectionProvider.getConnection();

		JSONArray jsonArray = new JSONArray();
		
		jsonArray=dao.selectNewAll(conn,ContentNumber,CommentLatestNumber);
		conn.close();
		PrintWriter out = response.getWriter();
		out.println( jsonArray);
		
		return null;
	}

}
