package com.sist.dao;

import java.util.Arrays;

public class MainClass {
	
	
	// 두 테이블의 데이터를 채우기 위해...
	// 1. movie 테이블에 theaterno 집어넣음 2. reservetheater 테이블 에 tdate 집어넣음 
	public static String movieRandomData(int count)
	{
		String result="";
		
		// 1. movie 테이블 - [극장 수]: 8~15개. 랜덤.
		// int no=(int)(Math.random()*8)+8; // (0~0.99)*8 + 8 ==> (0~7)+8 ==> 8~15 ==> 극장 수 8개~15개 
		// 2. reservetheater 테이블 - tdate를 10~15개 넣자 
		// int no=(int)(Math.random()*6)+10; // (0~0.99)*6 + 10 ==> (0~5)+10 ==> 10~15 
		// 3. 
		int no=(int)(Math.random()*4)+7; // (0~0.99)*4 + 7 ==> (0~3)+7 ==> 7~10 
		
		// [극장번호]: 중복 없는 랜덤. 
		// 1) 위에서 정의한 극장 수만큼만 생성됨. 2) 극장번호는 1~15번 사이임.  
		// ==> 즉, 총 개수 8~15개 사이의, 중복 없는극장번호 (극장번호: 1~15) 
		// ex) 극장번호 (1,3,5): ==> CGV 마포, CGV 홍대, 롯데시네마 구로	
		int[] com=new int[no];
		int rand=0;
		boolean bCheck=false;
		for(int i=0; i<no; i++) // 위의 no(극장수, 예약가능 날짜 수)만큼 for문 돌려서 랜덤극장번호/랜덤날짜를 집어넣는다  
		{
			bCheck=true;
			while(bCheck)
			{
				// 1. movie 테이블 처리용 - 극장번호 
				// rand=(int)(Math.random()*15)+1; // (0~14)+1 ==> 1~15 
				// 2. reservetheater 테이블 처리용 - 날짜 
				// rand=(int)(Math.random()*31)+1; // (0~30)+1 ==> 1~31
				// 3. reservedate 테이블 처리용 
				rand=(int)(Math.random()*29)+1; // (0~28)+1 ==> 1~29 ==> 예약가능시간대가 29개임 (난 사실 30개 만들었지만..) 
				bCheck=false;
				for(int j=0;j<i;j++)
				{
					if(com[j]==rand)
					{
						bCheck=true; // while문을 다시 돌린다. ==> 랜덤 다시 발생
					}
				}
			}
			com[i]=rand;
		}
		
		Arrays.sort(com);
		
		for(int i=0;i<com.length;i++)
		{
			result+=com[i]+",";
		}
		result=result.substring(0,result.lastIndexOf(",")); // 맨 마지막 콤마 제거한다
		System.out.println("result="+result);
		
		return result;
	}
	
	
	// [메인] 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. movie 테이블 처리용
		// for(int i=1;i<=24;i++) // 영화 총 개수가 24개라서..
		
		// 2. reservetheater 테이블 처리용 
		// for(int i=1;i<=15;i++)
		
		// 3. reservedate 테이블 처리용 
		for(int i=1;i<=31;i++)
		{
			String res=movieRandomData(15); // 영화관 수가 15개니까 
			System.out.println(i+"th Result="+res);
			System.out.println("==============================================");
			
			// 1. movie 테이블 처리용
			// MovieVO vo=new MovieVO();
			// vo.setMno(i);
			// vo.setTheaterno(res);
			// MovieDAO.movieTheaterUpdate(vo); // DB에 집어넣는다. - 주석 풀고 Run Java Application 하면 돌아감 
			
			// 2. reservetheater 테이블 처리용 
			// ReserveTheaterVO vo=new ReserveTheaterVO();
			// vo.setTno(i);
			// vo.setTdate(res);
			// MovieDAO.movieDateUpdate(vo); // DB에 집어넣는다 -  주석 풀고 Run Java Application 하면 돌아감 
			
			// 3. 
			ReserveDateVO vo=new ReserveDateVO();
			vo.setTno(i);
			vo.setTime(res);
			MovieDAO.movieTimeUpdate(vo);
		}
	}

}








