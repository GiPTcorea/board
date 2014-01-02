package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProcessHandler {
	
	public String process(HttpServletRequest request,HttpServletResponse response)throws Throwable;
}
