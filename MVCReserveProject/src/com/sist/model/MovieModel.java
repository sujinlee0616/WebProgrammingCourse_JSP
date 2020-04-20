package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;
import com.sist.dao.ReserveTheaterVO;

@Controller
public class MovieModel {
	
	// 아래의 세부 유닛(?)들을 모두 포함하는 페이지 
	@RequestMapping("movie/reserve.do")
	public String movie_reserve(HttpServletRequest request, HttpServletResponse response)
	{		
		return "reserve.jsp";
	}
	
	@RequestMapping("movie/movie.do")
	public String movie_movie(HttpServletRequest request, HttpServletResponse response)
	{
		// MovieDAO를 static으로 코딩해놨으니까 새로 생성하지 않아도 됨
		List<MovieVO> list=MovieDAO.movieListData();
		request.setAttribute("mList", list);
		
		return "movie.jsp";
	}
	
	// []
	@RequestMapping("movie/theater.do")
	public String movie_theater(HttpServletRequest request,HttpServletResponse response)
	{
		String tno=request.getParameter("tno");
		System.out.println(tno);
		
		// DAO 연결. 결과값을 theater.jsp로 전송 (==> reserve.jsp에서 읽어감) 
		StringTokenizer st=new StringTokenizer(tno,","); // 극장번호가 (2,3,1) 이런식으로 ,으로 구분되어 있으니까.. 잘라야...
		List<ReserveTheaterVO> list = new ArrayList<ReserveTheaterVO>();		
		
		while(st.hasMoreTokens()) 
		{
			ReserveTheaterVO vo=MovieDAO.movieTheaterData(Integer.parseInt(st.nextToken()));
			System.out.println("vo.getTno()="+vo.getTno()+", vo.getTloc()="+vo.getTloc());
			list.add(vo);
		}
		
		request.setAttribute("tList", list);
		
		return "theater.jsp";
	}
	
	// [날짜] - 세번째 영역
	@RequestMapping("movie/date.do")
	public String movie_date(HttpServletRequest request,HttpServletResponse response)
	{
		String strYear=request.getParameter("year");
		String strMonth=request.getParameter("month");
		
		String today=new SimpleDateFormat("yyyy-M-d").format(new Date());
		StringTokenizer st=new StringTokenizer(today,"-");
		
		String sy=st.nextToken();
		String sm=st.nextToken();
		String sd=st.nextToken();
		
		if(strYear==null)
			strYear=sy;
		if(strMonth==null)
			strMonth=sm;
		
		int year=Integer.parseInt(strYear);
		int month=Integer.parseInt(strMonth);
		int day=Integer.parseInt(sd);
		String[] strWeek={"일","월","화","수","목","금","토"};
		
		// 전년도까지의 날짜의 합 
		int total=(year-1)*365
				+(year-1)/4
				-(year-1)/100
				+(year-1)/400; 
		
		// 윤년처리 
		int[] lastDay={31,28,31,30,31,30,
						31,31,30,31,30,31};
		if((year%4==0 && year%100!=0) || (year%400==0))
			lastDay[1]=29;
		else
			lastDay[1]=28;
		
		// 전달까지의 날짜의 합 
		for(int i=0;i<month-1;i++)
		{
			total+=lastDay[i];
		}
		
		total++; // 이번달의 1일자까지의 날짜의 합 	
		int week=total%7; // 이번달의 1일자의 날짜의 합을 7로 나눈 나머지 ===> 이번달 1일이 무슨 요일인지 알기 위해서 
		
		request.setAttribute("lastday", lastDay[month-1]); 
		request.setAttribute("week", week); // 이번달 1일이 무슨 요일인지 ==> 이 날부터 출력한다.  
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("strWeek", strWeek);
		
		return "date.jsp";
	}
	
	
	
	
	
	
}





