package ContentService;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;
import DAO.MeTooDAO;

public class IncreaseMetooCnt implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		int contentNumber = Integer.parseInt(request
				.getParameter("contentNumber"));
		HttpSession session = request.getSession();
		int uid = (int) session.getAttribute("uid");

		Connection conn = ConnectionProvider.getConnection();
		ContentDAO contentDAO = ContentDAO.getInstance();
		MeTooDAO metooDAO = MeTooDAO.getInstance();

		JSONObject jsonObject = new JSONObject();
		int metooCnt=-1;
		
		if (metooDAO.select(conn, uid, contentNumber) != 1) {
			if (contentDAO.CntSet(conn, contentNumber, "increaseMeTooCnt") == 0) {
				jsonObject.put("isOkay", "false");
			} else {
				metooDAO.insert(conn,uid, contentNumber);
				metooCnt=contentDAO.count(conn, contentNumber);
				jsonObject.put("isOkay", metooCnt);
			}
		}else{
			jsonObject.put("isOkay", "dupli");
		}
		conn.close();
		PrintWriter out = response.getWriter();
		out.println(jsonObject);

		return null;
	}

}
