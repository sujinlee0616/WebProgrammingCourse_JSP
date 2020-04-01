// 2020.04.01 수업 
// 메소드를 어떻게 찾아오는지 알아보자. (Annotation에 따라 적절한 메소드 찾아오게 하는 기능을 구현해보자.) 
// @RequestMapping("xxx.do") 어노테이션으로 적절한 메소드를 찾아올 수 있다.   
package com.sist.temp;
import java.lang.reflect.Method;
import java.util.*;

class A
{
	// 메소드가 여러개일 때, @RequsetMapping을 이용해서 메소드 찾아오게 할 수 있다. ★★★★
	// ex) a.do (url에 ~~/a.do 들어왔을 때)
	//     ==> @RequestMapping("a.do") 되어 있으면 , a.do 받으면 aaa 메소드 찾아와준다. 	
	
	@RequestMapping("a.do")
	public void aaa()
	{
		System.out.println("A:aaa() Call...");
	}
	
	@RequestMapping("b.do")
	public void bbb()
	{
		System.out.println("A:bbb() Call...");
	}
	
	@RequestMapping("c.do")
	public void ccc()
	{
		System.out.println("A:ccc() Call...");
	}
	
	@RequestMapping("d.do")
	public void ddd()
	{
		System.out.println("A:ddd() Call...");
	}
	
	@RequestMapping("e.do")
	public void eee()
	{
		System.out.println("A:eee() Call...");
	}
}
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		// a.do 라고 입력하면 aaa를 호출
		// b.do 라고 입력하면 bbb를 호출 
		
		System.out.println("URI 주소 입력:");
		String uri=scan.next();
		
		/*A a = new A();
		if(uri.equals("a.do"))
			a.aaa();
		if(uri.equals("b.do"))
			a.bbb();
		if(uri.equals("c.do"))
			a.ccc();
		if(uri.equals("d.do"))
			a.ddd();
		if(uri.equals("e.do"))
			a.eee();*/
		
		try
		{
			// 1. 메모리 할당 
			Class clsName=Class.forName("com.sist.temp.A"); // Class.forName(package명) ==> 
			Object obj=clsName.newInstance(); 
			// 위의 두 줄은 A a = new A(); 와 똑같은 코딩임
			
			Method[] methods=clsName.getDeclaredMethods();
			for(Method m:methods) 
			{
				// System.out.println(m.getName()); // 내가 입력한 순서와 맞지 않게 나옴 ㅠㅠ 빈 공간게 걍 막 집어넣는거라서.. 순서대로 집어넣는게 아니라서 그럼..
				
				RequestMapping rm=m.getAnnotation(RequestMapping.class);
				if(rm.value().equals(uri)) // value 함수: RequestMapping.java에 만들어놓은 것.
				{
					m.invoke(obj, null); // invoke: 
				}
				
			}
					
					
			
		}catch(Exception ex){}
	}

}
