package Command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

	private Map<String, ProcessHandler> commandHandlerMap = new HashMap<String, ProcessHandler>();

	public void init() throws ServletException {

		String configFile = getInitParameter("configFile");
		String configFilePath = getServletContext().getRealPath(configFile);
		FileInputStream fis = null;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(configFilePath);
			prop.load(fis);

			if (fis != null) {
				fis.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator iter = prop.keySet().iterator();
		String command = null;
		String handlerClassName = null;

		while (iter.hasNext()) {
			command = iter.next().toString();
			handlerClassName = prop.getProperty(command);
			try {

				Class<?> handlerClass = Class.forName(handlerClassName);
				ProcessHandler handlerInstance = (ProcessHandler) handlerClass
						.newInstance();
				commandHandlerMap.put(command, handlerInstance);

			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}

		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/*String cmd = request.getParameter("cmd");
		if (cmd == null) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/Error/accessError.jsp");
			dispatcher.forward(request, response);
		} else {*/
			processRequest(request, response);
		//}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String nextView = null;
		ProcessHandler handler = commandHandlerMap.get(cmd);

		if (handler == null) {
			handler = new NullHandler();
		}

		try {
			nextView = handler.process(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		if (nextView != null) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(nextView);
			dispatcher.forward(request, response);
		}
	}
}
