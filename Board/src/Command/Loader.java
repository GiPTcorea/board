package Command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import DAO.CommentDAO;
import DAO.ContentDAO;
import DAO.MeTooDAO;

public class Loader extends HttpServlet {
	public void init(ServletConfig config) {
		
		String jdbcDriver =config.getInitParameter("jdbcdriver");
		StringTokenizer st = new StringTokenizer(jdbcDriver);
		StringBuffer sb = new StringBuffer();

		try {
			
			while (st.hasMoreTokens()) {
				sb.append(st.nextToken());
				Class.forName(sb.toString());
				sb.delete(0, sb.length());
			}
			
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		Connection conn=null;
		ContentDAO contentDAO = ContentDAO.getInstance();
		CommentDAO commentDAO = CommentDAO.getInstance(); 
		MeTooDAO metooDAO = MeTooDAO.getInstance();
		try {
			conn = ConnectionProvider.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contentDAO.create(conn);
		commentDAO.create(conn);
		metooDAO.create(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
