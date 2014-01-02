package ContentService;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;

public class SelectAllContent implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		int ContentNumber=Integer.parseInt(request.getParameter("ContentNumber"));

		ContentDAO contentDAO=ContentDAO.getInstance();
		Connection conn=ConnectionProvider.getConnection();

		JSONArray jsonArray = new JSONArray();
		
		if(ContentNumber<0)
		{
			 jsonArray=contentDAO.selectAll(conn,0);
		}else{
			 jsonArray=contentDAO.selectAll(conn,ContentNumber);
		}
		
		
		conn.close();
		PrintWriter out = response.getWriter();
		out.println( jsonArray);

		return null;
	}

}
