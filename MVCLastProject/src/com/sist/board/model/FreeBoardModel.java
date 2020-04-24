package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardReplyVO;
import com.sist.vo.BoardVO;

@Controller
public class FreeBoardModel {
	
	@RequestMapping("freeboard/list.do")
	public String freeBoardList(HttpServletRequest request,HttpServletResponse response)
	{
		// page 
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		FreeBoardDAO dao=new FreeBoardDAO();
		List<BoardVO> list=dao.freeboardListData(curpage);
		int totalpage=dao.freeBoardTotalPage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	
	// [글쓰기] - 화면만 보여줌 
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	// [글쓰기] - 실제 처리 
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request,HttpServletResponse response)
	{
		// 한글변환
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
		// 송신:인코딩, 수신:디코딩 
		// ASC (1byte) => Unicode(2byte)
		
		// 메소드 ==> 매개변수가 3개 이상이면 클래스로 묶어서 전송 
		//                            =========== 이 클래스를 우리는 'VO'라고 이름지어줬음. (데이터형을 만들어서 한 번에 보내겠다!) 
		// - VO는 기능은 없지만 여러 변수를 묶어줌 :) 
		
		// 사용자가 입력해서 보내준 데이터 받아서 
		String name=request.getParameter("name"); // <input>의 name 속성으로 파라미터를 받는다 
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		// 데이터 잘 받는지 확인 
		// System.out.println("name="+name+", subject="+subject+", content="+content+", pwd="+pwd);
		
		// VO에다가 담는다
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO로 전송 ==> DAO에서 오라클로 보내준다 
		FreeBoardDAO dao=new FreeBoardDAO();
		dao.freeBoardInsertData(vo); 
		
		return "redirect:../freeboard/list.do";
	}
	
	// [상세페이지 보기]
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request,HttpServletResponse response)
	{
		// =================================== 상세보기 ===================================
		// 사용자 요청 데이터 (no) 받기
		String no=request.getParameter("no");

		// DAO 연동 
		FreeBoardDAO dao=new FreeBoardDAO();
		// VO를 받아서 JSP로 보내준다.
		BoardVO vo=dao.freeBoardInfoData(Integer.parseInt(no),1); // 두번째 parameter는 type임. type=1이면 상세보기, type=2면 수정하기
		
		request.setAttribute("vo", vo);
		
		// =================================== 댓글 ===================================
		
		// [1. 댓글 목록] 
		String page=request.getParameter("page"); 
		if(page==null)
			page="1";
		System.out.println("page="+page);
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=rowSize*(curpage-1)+1; // 시작 댓글
		int end=curpage*rowSize; // 마지막 댓글 
		map.put("pStart", start); // freeboard-reply-mapper.xml에서 설정한 key값 
		map.put("pEnd", end);
		map.put("pBno", Integer.parseInt(no)); 
		System.out.println("map="+map);
		List<BoardReplyVO> list=FreeBoardReplyDAO.replyListData(map);
		System.out.println("Model list="+list);
		
		// [2. 댓글 총 페이지 가져옴] 
		map=new HashMap(); // map 새로 생성 ==> 앞에서 썼떤 map이 아닌 새로운 map 
		map.put("pBno", Integer.parseInt(no));
		int totalpage=FreeBoardReplyDAO.replyTotalPage(map);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage); 
		
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	// [글 수정] - 수정할 데이터 보여주기
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request,HttpServletResponse response)
	{
		// 사용자 요청 데이터 (no) 받기
		String no=request.getParameter("no");
		
		// DAO 연동 
		FreeBoardDAO dao=new FreeBoardDAO();
		
		// VO를 받아서 JSP로 보내준다.
		BoardVO vo=dao.freeBoardInfoData(Integer.parseInt(no),2); // 두번째 parameter는 type임. type=1이면 상세보기, type=2면 수정하기 
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	}
	
	// [글 수정] - 실제로 데이터 업데이트 해주기 
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request,HttpServletResponse response)
	{
		// 한글변환
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}

		String no=request.getParameter("no");
		String name=request.getParameter("name");  
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		// VO에다가 담는다
		BoardVO vo = new BoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO로 전송 ==> DAO에서 오라클로 보내준다 
		FreeBoardDAO dao=new FreeBoardDAO();
		dao.freeBoardUpdateData(vo); 
		
		// 프로시져에서 비번 맞게 입력했는지를 true/false로 보냄 ==> DAO에서 bCheck에 저장 ==> Model에서 받음 ==> update_ok.jsp로 보냄
		// 프로시져에서 true면 데이터 update하고 false면 update 안 함  
		boolean bCheck=dao.freeBoardUpdateData(vo);
		// update_ok.jsp에서 bCheck값 판별해서 true면 redirect하고 false면 비번 틀렸다고 얼럿날림
		
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("no", no);
		
		return "../freeboard/update_ok.jsp"; 
	}
	
	// [삭제] - 걍 화면만 보여줌 
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		request.setAttribute("no", no);
		request.setAttribute("main_jsp", "../freeboard/delete.jsp");
		return "../main/main.jsp";
	}
	
	// [삭제] - 실제 삭제처리
	@RequestMapping("freeboard/delete_ok.do")
	public String freeboard_detail_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		
		// DAO 연결 
		FreeBoardDAO dao=new FreeBoardDAO();
		
		// 프로시져에서 비번 맞게 입력했는지를 true/false로 보냄 ==> DAO에서 bCheck에 저장 ==> Model에서 받음 ==> delete_ok.jsp로 보냄
		// 프로시져에서 true면 데이터 delete하고 false면 delete 안 함  
		boolean bCheck=dao.freeBoardDeleteData(Integer.parseInt(no),pwd);
		// delete_ok.jsp에서 bCheck값 판별해서 true면 redirect하고 false면 비번 틀렸다고 얼럿날림
		
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("no", no);
		
		return "../freeboard/delete_ok.jsp";
	}
	
	
	// [댓글쓰기]
	@RequestMapping("freeboard/reply_insert.do")
	public String freeboard_reply_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
		
		String bno=request.getParameter("bno");
		String msg= request.getParameter("msg");
		
		// insert 처리 
		// ID 가져오기 ★★★
		//  - request를 통해서 Session, Cookie를 얻을 수 있다. ★★★
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		 
		// DAO => Map에 묶어서 전송
		Map map=new HashMap();
		// 원래는	Map<String, Object> 이렇게 써야함. 앞에껀 key("id"), 뒤에껀 value(int니까..)
		map.put("pBno",Integer.parseInt(bno));
		map.put("pId", id);
		map.put("pName", name);
		map.put("pMsg", msg);
		
		FreeBoardReplyDAO.replyInsert(map);
		
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	
	// [댓글 수정]
	@RequestMapping("freeboard/reply_update.do")
	public String freeboard_reply_update(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
		
		String bno=request.getParameter("bno");
		String no=request.getParameter("no");
		String msg=request.getParameter("msg");
		
		Map map=new HashMap();
		map.put("pNo", Integer.parseInt(no));
		map.put("pMsg", msg);
		
		FreeBoardReplyDAO.replyUpdate(map);
		
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	
	// [대댓글 쓰기]
	@RequestMapping("freeboard/reply_reply_insert.do")
	public String freeboard_reply_reply_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
		
		String bno=request.getParameter("bno");
		String pno=request.getParameter("pno");
		String msg=request.getParameter("msg");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		Map map=new HashMap();
		map.put("pBno", Integer.parseInt(bno));
		map.put("pPno", Integer.parseInt(pno));
		map.put("pId", id);
		map.put("pName", name);
		map.put("pMsg", msg);
		
		FreeBoardReplyDAO.replyReplyInsert(map);
		
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	
	// [댓글 삭제]
	@RequestMapping("freeboard/reply_delete.do")
	public String freeboard_reply_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String bno=request.getParameter("bno");
		
		Map map=new HashMap();
		map.put("pNo",Integer.parseInt(no));
		
		FreeBoardReplyDAO.replyReplyDelete(map);
		
		return "redirect:../freeboard/detail.do?no="+bno;
	}
	
	
}






