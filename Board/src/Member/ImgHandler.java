package Member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Command.ConnectionProvider;
import Command.ProcessHandler;
import DAO.CommentDAO;
import DAO.ContentDAO;
import DAO.MemberDAO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ImgHandler implements ProcessHandler  {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String fileurl = "/opt/lampp/htdocs/tomcat/apache-tomcat/webapps/Board/img"; // 파일저장경로 절대경로임.
		String encType="UTF-8"; // 파일 인코딩방식
		int Maxsize = 5*1024*1024; // 사진용량제한 5Mb
		//ServletContext context=request.getServletContext();
		HttpSession session = request.getSession();
		String uid = session.getAttribute("uid").toString();
		JSONObject jsonObject=null;
		String realFileName=null;
		try{
			
			MultipartRequest multi = null;
			DefaultFileRenamePolicy policy= new DefaultFileRenamePolicy(); // 중복이름파일의 업로드방식설정.
			
			multi = new MultipartRequest(request,fileurl,Maxsize,encType,policy); // 파일업로드.
			
			//나머지 파일업로드 위치와 이름 확인.
			Enumeration files = multi.getFileNames();
			String filename="";
			String name = (String)files.nextElement();
			filename = (String)multi.getFilesystemName(name);
			//File file = multi.getFile(name);
			//String ext = multi.getContentType(name);
			
			
			
			String fileName = filename.trim();  
		   // String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
		    int i = -1;
		          i = fileName.lastIndexOf("."); // 파일 확장자 위치
		          String type = fileName.substring(i, fileName.length()); 
		          realFileName= uid + type; //현재시간과 확장자 합치기
		    
		          
		    File oldFile = new File(fileurl +"/"+ filename);
		    File newFile = new File(fileurl +"/"+ realFileName); 
		    if(newFile.exists()){
		    	newFile.delete();
		    }
		    oldFile.renameTo(newFile);
			jsonObject =new JSONObject();
			jsonObject.put("type","/Board/img/"+realFileName);

		}catch(IOException ioe){
			System.out.println(ioe);
			
		}

		PrintWriter out = response.getWriter();
		out.println(jsonObject);

		Connection conn =ConnectionProvider.getConnection();
		MemberDAO memberdao =MemberDAO.getInstance();
		ContentDAO contentdao = ContentDAO.getInstance();
		CommentDAO commentdao = CommentDAO.getInstance();
		int intid = Integer.parseInt(uid);
		memberdao.changeImg(conn, intid, "/Board/img/"+realFileName);
		contentdao.changeImg(conn, intid, "/Board/img/"+realFileName);
		commentdao.changeImg(conn, intid, "/Board/img/"+realFileName);
		return null;
		
	}

}
