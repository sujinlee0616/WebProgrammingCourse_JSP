package com.sist.dao;

import java.util.Arrays;

public class MainClass {

	// ReserveDate 테이블(tno,time)에 값을 (날짜, null)로 넣어놔서... null이 아니라 랜덤하게 time값을 데이터를 넣어주자.
	public static String movieRandomData(int count)
	{
		String result="";
		
		// [극장 수]: 8~15개. 랜덤.
		int no=(int)(Math.random()*8)+8; // (0~0.99)*8 + 8 ==> (0~7)+8 ==> 8~15 ==> 극장 수 8개~15개 
		
		// [극장번호]: 중복 없는 랜덤. 
		// 1) 위에서 정의한 극장 수만큼만 생성됨. 2) 극장번호는 1~15번 사이임.  
		// ==> 즉, 총 개수 8~15개 사이의, 중복 없는극장번호 (극장번호: 1~15) 
		// ex) 극장번호 (1,3,5): ==> CGV 마포, CGV 홍대, 롯데시네마 구로	
		int[] com=new int[no];
		int rand=0;
		boolean bCheck=false;
		for(int i=0; i<no; i++)
		{
			bCheck=true;
			while(bCheck)
			{
				rand=(int)(Math.random()*15)+1; // (0~14)+1 ==> 1~15
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
		for(int i=1;i<=24;i++) // 영화 총 개수가 24개라서..
		{
			String res=movieRandomData(15);
			System.out.println(i+"th Result="+res);
			System.out.println("==============================================");
			
			MovieVO vo=new MovieVO();
			vo.setMno(i);
			vo.setTheaterno(res);
			// MovieDAO.movieTheaterUpdate(vo); // DB에 집어넣는다. - 주석 풀고 Run Java Application 하면 돌아감 
			
			
		}
	}

}








