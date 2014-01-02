package Member;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.MemberDAO;
import DTO.MemberDTO;

public class UpdateProfile implements ProcessHandler  {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int uid = (int) session.getAttribute("uid");
		String name = request.getParameter("field_name");
		String age = request.getParameter("field_age");
		String phone = request.getParameter("field_phone");
		String nick = request.getParameter("field_nick");
		String car = request.getParameter("field_car");
		String ton = request.getParameter("field_ton");
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setName(name);
		memberDTO.setAge(age);
		memberDTO.setCar(car);
		memberDTO.setNickName(nick);
		memberDTO.setTon(ton);
		memberDTO.setPhone(phone);
		
		MemberDAO memberDAO =MemberDAO.getInstance();
		Connection conn = ConnectionProvider.getConnection();
		memberDAO.update(conn,memberDTO, uid);
		
		conn.close();
		
		return "/Board/MyPage.jsp";
	}

}
