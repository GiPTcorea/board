package ChangePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.ProcessHandler;

public class GoBoard implements ProcessHandler{

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		return "/Board/Board.jsp";
	}

}
