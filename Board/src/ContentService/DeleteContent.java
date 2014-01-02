package ContentService;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;

public class DeleteContent implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		int number =Integer.parseInt(request.getParameter("ContentNumber"));
		
		Connection conn =ConnectionProvider.getConnection();
		
		ContentDAO dao = ContentDAO.getInstance();
		
		dao.delete(conn, number);
		conn.close();
		return "/Board/MyPage.jsp";
	}

}
