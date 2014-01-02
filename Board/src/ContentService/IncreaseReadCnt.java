package ContentService;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;

public class IncreaseReadCnt implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		int contentNumber = Integer.parseInt(request
				.getParameter("contentNumber"));

		Connection conn = ConnectionProvider.getConnection();
		ContentDAO contentDAO = ContentDAO.getInstance();
		JSONObject jsonObject = null;
		if (contentDAO.CntSet(conn, contentNumber, "increseReadCnt") == 0) {
			
			jsonObject = new JSONObject();
			jsonObject.put("isOkay", false);
		}
		conn.close();
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());

		return null;
	}

}
