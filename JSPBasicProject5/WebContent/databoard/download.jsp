<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, java.net.*"%>
<%
	/* 한글 인코딩 처리 해줘야할까? No. */
	/* POST 방식은 setCharacterEncoding해줘야 하지만, GET 방식은 톰캣에서 처리하기 때문. */
	/* download.jsp는 GET 방식이니까... */
	
	String fn=request.getParameter("fn"); // fn: 파일이름
	
	// 1.다운받을건지 물어봄.(header로)
	response.setHeader("Content-Disposition", "attachment;filename="
			+URLEncoder.encode(fn,"UTF-8"));
	// Content-Disposition: 브라우저 하단에 다운로드 창 띄워주는 애 

	// 2. 파일명과 파일 크기 알려줌 
	File file=new File("c:\\upload\\"+fn);
	response.setContentLength((int)file.length());
	
	// 실제 파일 데이터 보내줌 
	try
	{
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		int i=0;
		// 데이터 쪼개서 보내줌 - 더 효율적. TCP는 1024byte가 가장 효율적.
		byte[] buffer=new byte[1024]; //1024 byte씩 쪼개서 progress bar 진행...
		while((i=bis.read(buffer,0,1024))!=-1)  // i: 몇 바이트 채웠는지 확인하는 애. // -1:끝났다 는 뜻 
		{
			bos.write(buffer,0,i);
		}
		
		/* ★★★pageContext 써서 기존에 사용되고 있던 out 객체를 초기화시켜줘야함 ★★★  상세설명: https://dantes.kr/357   */
		out.clear();
		out=pageContext.pushBody(); 
		/* 위의 out 두 줄 쓰지 않으면 에러남 */
		/* Error: 심각: 경로 [/JSPBasicProject5]의 컨텍스트 내의 서블릿 [jsp]을(를) 위한  Servlet.service() 호출이, 근본 원인(root cause)과 함께, 예외 [java.lang.IllegalStateException: 이 응답을 위해 getOutputStream()이 이미 호출되었습니다.]을(를) 발생시켰습니다.) */
		
		bis.close();
		bos.close();
	}
	catch(Exception ex){}
	
%>