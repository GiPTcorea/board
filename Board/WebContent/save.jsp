<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page language="java" import="java.sql.*, java.text.*, java.util.*, java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<% request.setCharacterEncoding("euc-kr"); %>
<%
	String fileurl = "/opt/lampp/htdocs/tomcat/apache-tomcat/webapps/Board/img"; // 파일저장경로 절대경로임.
	String encType="UTF-8"; // 파일 인코딩방식
	int Maxsize = 5*1024*1024; // 사진용량제한 5Mb
	ServletContext context=getServletContext();
	try{
		
		MultipartRequest multi = null;
		DefaultFileRenamePolicy policy= new DefaultFileRenamePolicy(); // 중복이름파일의 업로드방식설정.
		multi = new MultipartRequest(request,fileurl,Maxsize,encType,policy); // 파일업로드.
		
		//나머지 파일업로드 위치와 이름 확인.
		Enumeration files = multi.getFileNames();
		String filename="";
		String name = (String)files.nextElement();
		filename = (String)multi.getFilesystemName(name);
		File file = multi.getFile(name);
		String ext = multi.getContentType(name);
		out.println(file+"."+ext);
		
		/*     
		*******
		multipartRequest는 파일명을 변경하여 올릴 수 없기 때문에 올린파일을 객체로 반환하여 이름을 변경.. 
		isuue눈 util과 Date()함수에서 에러나옴. import에서 뭘해야할지 모르겠음....
		*******
		
		
		String fileName =Util.nullOrEmptyToReplaceString(multi.getFilesystemName("File_url") ,"");  
	    String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
	    int i = -1;
	          i = fileName.lastIndexOf("."); // 파일 확장자 위치
	          String realFileName = now + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기
	    
	    File oldFile = new File(fileurl + fileName);
	    File newFile = new File(fileurl + realFileName); 
	    
	    oldFile.renameTo(newFile);
	    out.println(newFile.getPath()+" : "+ newFile.getName());;
	    */
		
		
		
		
		

		
	}catch(IOException ioe){
		out.println(ioe);
		
	}	
	/*
	try{
		Class.forName("org.git.mm.mysql.Driver");
		
	}catch(java.lang.ClassNotFoundException e){
		out.println(e.getMessage());
	}
	
	String sql=null;
	Connection conn = null;
	Statement st = null;
	ResultSet rs=null;
	
		
	
	try{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dboard?useUnicode=true&characterEndocing=euckr","root","giptkorea");
		st = conn.createStatement();
		
	}catch(SQLException e){
		out.print(e);
	}
	
	*/
	
		
%>