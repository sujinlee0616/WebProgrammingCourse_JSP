package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;

public class MovieListModel implements Model{

	@Override
	public String execute(HttpServletRequest request){
		
		// JSPMiddleProject3 > MovieModel > movieList 메소드..
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=9;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<MovieVO> list = MovieDAO.movieListData(map);
		int totalpage=MovieDAO.movieTotalPage();	
		
		// JSP로 결과값 전송 
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);	
		
		return "movie/movielist.jsp";
	}
}
