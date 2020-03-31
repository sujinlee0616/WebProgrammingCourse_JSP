package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class DetailModel implements Model {
	
	@Override
	public String execute(HttpServletRequest request){
		
		// // JSPMiddleProject5 > BoardModel > boardDetail 메소드 복붙.
		String no=request.getParameter("no");
		BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		
		return "board/detail.jsp"; // ==> forward됨  
	}
}
