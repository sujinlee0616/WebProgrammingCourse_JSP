package com.sist.temp;

import java.lang.reflect.Method;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Scanner scan=new Scanner(System.in);
			System.out.println("입력: ");
			String str=scan.next();
			
			Class acls=Class.forName("com.sist.temp.A"); // 리플렉션 
			// A a=(A)acls.newInstance();
			// a.display();
			Object obj=acls.newInstance();
			Method[] m=acls.getDeclaredMethods(); // A 클래스가 갖고 있는 모든 메소드 요청
			/*m[0].invoke(obj, null); */
			
			/*for(Method mm:m)
			{
				RequestMapping rm=mm.getDeclaredAnnotation(RequestMapping.class);
				if(rm.value().equals(str))
				{
					mm.invoke(obj, null);
				}

			}*/
			
		}
		catch(Exception ex){}
	}
}





