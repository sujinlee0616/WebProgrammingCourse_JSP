package com.sist.board.model;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.ReplyBoardDAO;
import com.sist.vo.BoardVO;

@Controller
public class ReplyBoardModel {

	// [리스트] 
	@RequestMapping("reply/list.do")
	public String reply_list(HttpServletRequest request, HttpServletResponse response)
	{
		// 요청 데이터 갖고 온다
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=15;
		int start = rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		// DAO 
		List<BoardVO> list=ReplyBoardDAO.replyListData(map);
		int totalpage=ReplyBoardDAO.replyTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		/*
		 * 위의 코드는 Spring에서는 아래와 같이 사용한다.  (참고: https://galid1.tistory.com/504)  
		 * <Spring 코드>
		 * class Model
		 * {
		 * 		HttpServletRequest request;
		 * 		public Model(HttpServletRequest request)
		 * 		{
		 * 			this.request=request;
		 * 		}
		 * 		public void addAttribute(String key, Object obj)
		 * 		{
		 * 			request.setAttribute(key,obj);
		 * 		}
		 * }
		 * 
		 * Model model=new Model(request);
		 * model.addAttribute("list", list);
		 * 
		 * ※ 참고: Spring에서는 request를 Model이라는 클래스 안에 넣고 처리한다. 
		 */		
		
		request.setAttribute("main_jsp", "../reply/list.jsp"); // main에 include시킴 
		return "../main/main.jsp";
	}
	
	
	
	// [상세페이지] 
	@RequestMapping("reply/detail.do")
	public String reply_detail(HttpServletRequest request, HttpServletResponse response)
	{
		// 요청 데이터 갖고 온다
		String no = request.getParameter("no");
		
		// DAO 
		BoardVO vo = ReplyBoardDAO.replyDetailData(Integer.parseInt(no));
		vo=ReplyBoardDAO.hitIncrement(Integer.parseInt(no));
				
		request.setAttribute("vo", vo);		
		
		request.setAttribute("main_jsp", "../reply/detail.jsp"); // main에 include시킴 
		return "../main/main.jsp";
		
	}
	
	// [새 글 작성]
	@RequestMapping("reply/insert.do")
	public String reply_insert(HttpServletRequest request, HttpServletResponse response)
	{		
		request.setAttribute("main_jsp", "../reply/insert.jsp"); // main에 include시킴 
		return "../main/main.jsp";
	}
	
	// [새 글 작성] 
	@RequestMapping("reply/insert_ok.do")
	public String reply_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		
		
		// 클라이언트가 입력한 데이터를 가지고 와야...
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		// 클라이언트가 입력해준 데이터 VO에 저장 
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);	
		
		// VO를 INSERT 하게 mapper에서 수행 
		ReplyBoardDAO.replyInsertData(vo);
		
		return "redirect:../reply/list.do";
	}
	
	// [글 수정] - 기존 글의 데이터 가져옴 
	@RequestMapping("reply/update.do")
	public String reply_update(HttpServletRequest request, HttpServletResponse response)
	{
		// 요청 데이터 갖고 온다
		String no = request.getParameter("no");
				
		// DAO - 해당 글의 데이터 보여준다. 
		BoardVO vo=ReplyBoardDAO.replyDetailData((Integer.parseInt(no)));	
		
		request.setAttribute("vo", vo);		
				
		request.setAttribute("main_jsp", "../reply/update.jsp"); // main에 include시킴 
		return "../main/main.jsp";
	}
	
	
	// [글 수정] - 비번 체크 
	@RequestMapping("reply/password_check.do")
	public String reply_password_check(HttpServletRequest request, HttpServletResponse response)
	{
		// 클라이언트 데이터 
		String no=request.getParameter("no");
		String user_input_pwd=request.getParameter("pwd"); // 클라이언트가 입력한 데이터  
		System.out.println("user_input_pwd="+user_input_pwd);
		
		// 클라이언트가 입력한 비번과 DB의 실제비번이 같은지 확인
		String db_pwd=ReplyBoardDAO.replyCheckRealPwd(Integer.parseInt(no)); // 이게 수행이 안 됨 
		System.out.println("db_pwd="+db_pwd);
		
		int res=0;
		if(db_pwd.equals(user_input_pwd))
		{
			res=1;
		}
		else
		{
			res=0;
		}
		request.setAttribute("result", res);
		
		return "../reply/password_check.jsp";
	}
	
	
	// [글 수정] - 실제 수정. update.
	@RequestMapping("reply/update_ok.do")
	public String reply_update_ok(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		
		// 클라이언트가 수정한 데이터를 가지고 와서 
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");  // 사용자가 입력한 비번을 가지고 와서 
			 
		// 클라이언트가 수정한 데이터를 VO에 저장 
		BoardVO vo = new BoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		ReplyBoardDAO.replyUpdateData(vo);		
		
		return "redirect:../reply/detail.do?no="+no;
	}
	
	// [답글달기] 
	@RequestMapping("reply/reply.do")
	public String reply_reply(HttpServletRequest request, HttpServletResponse response)
	{
		String pno=request.getParameter("no"); // 엄마글의 번호를 파라미터로 받아옴
		request.setAttribute("pno", pno); // 엄마글의 번호를 request에 싣는다 - reply.jsp에서 form의 action으로 reply_ok.do로 pno를 넘길거라서..
		
		request.setAttribute("main_jsp", "../reply/reply.jsp"); // main에 include시킴 
		return "../main/main.jsp";
	}
	
	// [답글달기] 
	@RequestMapping("reply/reply_ok.do")
	public String reply_reply_ok(HttpServletRequest request, HttpServletResponse response)
	{	
		try
		{
			request.setCharacterEncoding("UTF-8");   // 이 코드가 제일 최상위에 있어야 한글 안 깨짐.
		}catch(Exception ex){}
		
		String pno=request.getParameter("pno"); // 엄마 글 번호 넘기고 
		
		// 클라이언트가 입력한 데이터를 가지고 와야...
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		// 클라이언트가 입력해준 데이터 VO에 저장 
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);	
		
		// DAO 연결-이 vo를 가지고 답글을 isnert하게 
		ReplyBoardDAO.replyReplyData(Integer.parseInt(pno), vo);
				
		return "redirect:../reply/list.do";
		/*
		 * 	Q. redirect하는 것과 include 하는 것의 차이? 
		 *  ( return "redirect:../reply/list.do"; 하는 것과 
		 *    request.setAttribute("main_jsp", "../reply/list.jsp");
			  return "../main/main.jsp"; 하는 것의 차이  )  
		 * 	 - redirect하면 list.do 니까 list.do 들어올 때 메소드를 실행하는 것. 
		 *   - 이에 반해, incldue는 list.do하는게 아니니까 @RequestMapping("reply/list.do") 밑의 메소드를 실행시키지 않는다. 
		 * 
		 * 
		 */
	}
	
	// [삭제하기] 
	@RequestMapping("reply/delete.do")
	public String reply_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no"); 
		request.setAttribute("no", no); // no를 request에 싣는다 - delete.jsp에서 form의 action으로 delete_ok.do로 no를 넘길거라서...
		
		request.setAttribute("main_jsp", "../reply/delete.jsp"); // main에 include시킴 - main은 delete.jsp와 request 공유함
		return "../main/main.jsp";
	}
	
	// [삭제하기] 
	@RequestMapping("reply/delete_ok.do")
	public String reply_delete_ok(HttpServletRequest request,HttpServletResponse response)
	{
		// delete.jsp에서 hidden input으로 받은 데이터 두개를 받는다 
		String no=request.getParameter("no");  
		String pwd=request.getParameter("pwd"); 
		
		// DAO 연결  
		boolean bCheck=ReplyBoardDAO.replyDeleteData(Integer.parseInt(no),pwd);
		
		request.setAttribute("bCheck", bCheck);
		
		return "../reply/delete_ok.jsp";  // 비번 체크 후, 비번 맞을 때의 동작이랑 비번 틀릴 때의 동작 달라야. 비번 유효성 체크를 delete_ok.jsp에서 alert로 해줄거라서 일로 넘겼음
	}
	
	
}






