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
		// ========================== 달력 만들기 ==========================
		// 사용자가 요청한 날짜를 받는다 
		String strYear=request.getParameter("year");
		String strMonth=request.getParameter("month");
		String reserve_date=request.getParameter("rdate");
		
		String today=new SimpleDateFormat("yyyy-M-d").format(new Date()); // 오늘일자 
		StringTokenizer st=new StringTokenizer(today,"-");
		
		String sy=st.nextToken(); // 오늘일자에서의 '년'
		String sm=st.nextToken(); // 오늘일자에서의 '월'
		String sd=st.nextToken(); // 오늘일자에서의 '일' 
		
		// 사용자가 년/월/일을 지정을 따로 안 해줬으면, 오늘의 년도와 월을 넣어주자.
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
		
		// 얘네를 request에다가 싣어서 date.jsp로 보낸다 ==> date.jsp에서 이 정보를 활용해서 달력을 만든다.★  
		
		
		// ========================== 예약가능일자 ==========================
		int[] days=new int[31]; 
		// 배열 크기가 31인 배열을 만든다 ==> index는 0에서 30. ==> 인덱스 번호가 0~30임에 주의!!★
		// 왜 배열 크기가 31이냐? 당연하지 한 달이 31일이잖아...==> 모든 예약일을 다 커버 가능함 
		if(reserve_date!=null)
		{
			// reservedate: 1,2,7,8, ..
			StringTokenizer st1=new StringTokenizer(reserve_date,","); // reservedate를 ,기준으로 잘라서 배열에 넣는다 
			while(st1.hasMoreTokens())
			{
				int p=Integer.parseInt(st1.nextToken()); // reservedate를 int로 만들어주고 int[] days 배열에 넣는다   
				if(p>=day) // 오늘 이후의 예약일만 표시해야하니까  
				{
					days[p-1]=p; // 배열 index가 0~30이니까 -1 해줘야!!★
					// ex) 예약가능일이 2,3,8,21,28이면 day[1]=2, day[2]=3, day[7]=8, day[20]=21, day[27]28 이런식으로 저장됨 
				}
				
			}			
		}
		
		
		request.setAttribute("days", days);
		request.setAttribute("lastday", lastDay[month-1]); 
		request.setAttribute("week", week); // 이번달 1일이 무슨 요일인지 ==> 이 날부터 출력한다.  
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("strWeek", strWeek);
		
		return "date.jsp";
	}
	
	
	
	@RequestMapping("movie/time.do")
	public String movie_time(HttpServletRequest request,HttpServletResponse response)
	{
		String tno=request.getParameter("tno"); // 이게 값이 없나보네!!!!
		//System.out.println("tno="+tno);
		String times=MovieDAO.movieTimeData(Integer.parseInt(tno));  // 이게 값이 안 들어오네!!!!!!
		//System.out.println("times="+times);
		// ex) tno=3일때 time=3,4,7,11,14,27,28,29 <==이 time을 변수 times에 저장 
		StringTokenizer st=new StringTokenizer(times,","); // 이걸 잘라서 저장
		List<String> list=new ArrayList<String>();
		//System.out.println("=======================");
		
		while(st.hasMoreTokens()) // 자른 개수 모르니까 자른 회숫만큼 while문 돌림 
		{
			String time=MovieDAO.movieTimeData2(Integer.parseInt(st.nextToken()));
			// ex) 4번이면 9:30, 14번이면 14:30 이런 시간값을 time 변수에 저장  
			list.add(time); // list에 이 시간을 싣는다 ex) 9:30 
			//System.out.println("list="+list); 
		}
		
		request.setAttribute("tList", list);
		
		return "time.jsp";
	}
	
	@RequestMapping("movie/inwon.do")
	public String movie_inwon(HttpServletRequest request, HttpServletResponse response)
	{
		return "inwon.jsp";
	}
	
	@RequestMapping("movie/login.do")
	public String movie_login(HttpServletRequest request, HttpServletResponse response)
	{
		return "login.jsp";
	}
	
	@RequestMapping("movie/login_ok.do")
	public String movie_login_ok(HttpServletRequest request, HttpServletResponse response)
	{
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		// DAO 
		
		
		return "login_ok.jsp";
	}
	
	
}





