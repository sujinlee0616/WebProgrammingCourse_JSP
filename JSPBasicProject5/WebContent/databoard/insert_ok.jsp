<!-- ~~_ok.jsp : 값 받아서 DB에 넘겨주는 애. 화면 출력은 하지 않음. ==> HTML 코드 다 지운다. -->
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.oreilly.servlet.multipart.*,com.oreilly.servlet.*"%>
<!-- cos.jar 써야하니까 (파일업로드용..) import했음 -->
<%@ page import="java.io.*" %>
<%
	try{
		request.setCharacterEncoding("UTF-8");
	}
	catch(Exception ex){}
	
	//211.238.142.193
	String path="c:\\upload"; 
	int maxSize=100*1024*1024; // 파일크기 max=100MB
	String enctype="UTF-8";
			
	MultipartRequest mr=new MultipartRequest(request,path,maxSize,enctype,
			new DefaultFileRenamePolicy());
	// DefaultRenamePolicy: 동일한 파일명이 있으면 자동으로 (1), (2) 이렇게 붙여줌...
	
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	String filename=mr.getOriginalFileName("upload");
	String fn=mr.getFilesystemName("upload");
	
	FileBoardVO vo = new FileBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	if(filename==null)  // 파일 안 보냈다면 
	{
		vo.setFilename(""); 
		vo.setFilesize(0);
	}
	else  // 파일 보냈다면 
	{
		File file = new File(path+"\\"+fn); // filename(getOriginalFileName)이 아니라 fn(getFilesystemName) 써야!
		// 유저A가 a.txt 파일(핸드크림관련)을 업로드하려는데 이미 서버컴퓨터에는 a.txt 라는 (식품관련) 파일이 있음
		// ==> 서버컴퓨터에는 A가 업로드한 파일(핸드크림관련)이 a(1).txt라는 이름으로 올라감 
		// ==> 서버컴퓨터에게 있어 getOriginalFileName: a.txt, getFilesystemName: a(1).txt
		// ==> 변수 file에는 a(1).txt를 저장해야. ===> getFilesystemName 사용. 
		vo.setFilename(fn);
		vo.setFilesize((int)file.length());
	}
	
	FileBoardDAO dao = new FileBoardDAO();
	
	// DAO의 insert 함수 호출
	dao.boardInsert(vo);
	
	// 이동 
	response.sendRedirect("list.jsp"); 
	// insert_ok에서 받은 내용은 필요 없음. request 유지할 필요 없음 ==> forward가 아니라 sendRedirect 사용 
	
%>








