package ContentService;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.ContentDAO;
import DTO.ContentDTO;
import Util.DateUtil;

public class UpdateContent implements ProcessHandler {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String nextView ="/Board/MyPage.jsp";
		int contentNumber=Integer.parseInt(request.getParameter("ContentNumber"));
		
		DateUtil dateUtil = new DateUtil();
		HttpSession session=request.getSession();
		
		String content=request.getParameter("content");
		String date = dateUtil.getDate(2);
		
		
		ContentDTO dto = new ContentDTO();
		dto.setNumber(contentNumber);
		dto.setContent(content);
		
		dto.setUpdatedAt(date);

		Connection conn = ConnectionProvider.getConnection();

		ContentDAO dao =ContentDAO.getInstance();
		
		if(dao.update(conn,dto)!=1){
			//����
			nextView="/err.jsp";//���� ���� ��
		}
		conn.close();
		return nextView;
	}

}
