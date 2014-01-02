package ContentService;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;

public class SelectContent implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int contentNumber = Integer.parseInt(request
				.getParameter("contentNumber"));
		Connection conn = ConnectionProvider.getConnection();
		ContentDAO contentDAO = ContentDAO.getInstance();
		JSONObject jsonObject = new JSONObject();

		try {
			conn.setAutoCommit(false);
			contentDAO.CntSet(conn, contentNumber,"increaseReadCnt");
			jsonObject = contentDAO.select(conn, contentNumber);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
		}
		// if content doesnt exist JSONObject =null;
		// �ش� content readCount +1;
		// and get All content of Content;
		// put JSONObject;
		conn.close();
		PrintWriter out = response.getWriter();
		out.println(jsonObject);
		return null;
	}
}
