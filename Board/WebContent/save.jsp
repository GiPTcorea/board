<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page language="java" import="java.sql.*, java.text.*, java.util.*, java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<% request.setCharacterEncoding("euc-kr"); %>
<%
	String fileurl = "/opt/lampp/htdocs/tomcat/apache-tomcat/webapps/Board/img"; // ���������� ��������.
	String encType="UTF-8"; // ���� ���ڵ����
	int Maxsize = 5*1024*1024; // �����뷮���� 5Mb
	ServletContext context=getServletContext();
	try{
		
		MultipartRequest multi = null;
		DefaultFileRenamePolicy policy= new DefaultFileRenamePolicy(); // �ߺ��̸������� ���ε��ļ���.
		multi = new MultipartRequest(request,fileurl,Maxsize,encType,policy); // ���Ͼ��ε�.
		
		//������ ���Ͼ��ε� ��ġ�� �̸� Ȯ��.
		Enumeration files = multi.getFileNames();
		String filename="";
		String name = (String)files.nextElement();
		filename = (String)multi.getFilesystemName(name);
		File file = multi.getFile(name);
		String ext = multi.getContentType(name);
		out.println(file+"."+ext);
		
		/*     
		*******
		multipartRequest�� ���ϸ��� �����Ͽ� �ø� �� ���� ������ �ø������� ��ü�� ��ȯ�Ͽ� �̸��� ����.. 
		isuue�� util�� Date()�Լ����� ��������. import���� ���ؾ����� �𸣰���....
		*******
		
		
		String fileName =Util.nullOrEmptyToReplaceString(multi.getFilesystemName("File_url") ,"");  
	    String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //����ð�
	    int i = -1;
	          i = fileName.lastIndexOf("."); // ���� Ȯ���� ��ġ
	          String realFileName = now + fileName.substring(i, fileName.length());  //����ð��� Ȯ���� ��ġ��
	    
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