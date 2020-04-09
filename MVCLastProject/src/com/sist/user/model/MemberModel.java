package com.sist.user.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

@Controller
public class MemberModel {
	
	// [회원가입]
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../member/join.jsp"); // main.jsp에 include	
		return "../main/main.jsp";
	}
	
	// [우편번호 검색] - 단순히 postfind.jsp 창만 실행되게 만듦. 
	@RequestMapping("member/postfind.do")
	public String member_postfind(HttpServletRequest request, HttpServletResponse response)
	{
		return "../member/postfind.jsp";  // 얘는 main에 include시키지 않았음. 단독 ==> CSS 등을 자기 파일에 갖다놔야. 
	}
	
	// [우편번호 검색 결과]
	@RequestMapping("member/postfind_result.do")
	public String member_postfind_result(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
		
		String dong=request.getParameter("dong");
		List<ZipcodeVO> list = MemberDAO.postfindData(dong);
		
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		
		return "../member/postfind_result.jsp"; // 단독 실행. 자기가 처리.   
	}
	
	// [아이디 중복 체크] - 단순히 idcheck.jsp 창만 실행되게 만듦. 
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request, HttpServletResponse response)
	{
		return "../member/idcheck.jsp";
	}
	
	// [아이디 중복 체크] 
	@RequestMapping("member/idcheck_result.do")
	public String member_idcheck_result(HttpServletRequest request, HttpServletResponse response)
	{
		String id=request.getParameter("id"); //AJAX가 보내준 id 받는다
		int count=MemberDAO.idcheckData(id);
		request.setAttribute("count", count); 
		return "../member/idcheck_result.jsp"; // 자기 자신이 수행함. 
	}
	
	// [회원가입 완료] - join.jsp에서 join_frm 전송완료 - join_frm에 입력된 데이터를 DB에 넣기.
	@RequestMapping("member/join_ok.do")
	public String member_join_ok(HttpServletRequest request, HttpServletResponse response)
	{	
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
			
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String post1=request.getParameter("post1");
		String post2=request.getParameter("post2");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String tel3=request.getParameter("tel3");
		String email=request.getParameter("email");
		String content=request.getParameter("content");
		
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setEmail(email);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setContent(content);
		vo.setPost(post1+"-"+post2);
		vo.setTel(tel1+"-"+tel2+"-"+tel3);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
	
		// DB에 insert 
		MemberDAO.memberInsert(vo);
		
		return "redirect:../main/main.do";
	}
	
	// [로그인] 
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("id"); // getParameter 안의 "id"는 main.jsp의 login_frm의 name="id"임 
		String pwd=request.getParameter("pwd"); 
		
		// DAO 연결
		MemberVO vo=MemberDAO.memberLogin(id, pwd);
		
		// 로그인 판정결과가 OK라면(ID/PWD 모두 일치) 세션에다가 id,name,admin값 저장 
		if(vo.getMsg().equals("OK")) 
		{
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		
		// 로그인 판정 결과(ID/PWD 맞는지 검사 후 판정결과를 세가지(OK/NOPWD/NOID)로 구분해서 msg에 값 String으로 넣음
		request.setAttribute("msg", vo.getMsg()); 
		
		return "../member/login.jsp";
	}
	
	// [로그아웃]
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:../main/main.do";
	}
		
	
}









