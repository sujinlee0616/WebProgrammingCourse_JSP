package com.sist.controller;
import java.util.*;

public class A {
	Map map = new HashMap();
	public A()
	{
		map.put("b", new B());
	}
	
	public static void main(String[] args) {
		A a = new A();
		B b = (B)a.map.get("b"); // B b = new B(); 랑 똑같음...
		//    === 형변환... object가 들어오니까...
		B c = (B)a.map.get("b"); // B c = new B(); 랑 똑같음
		System.out.println("b="+b); // b의 주소값 확인
		System.out.println("c="+c); // c의 주소값 확인
		// b와 c의 주소값이 같음을 볼 수 있다.
		// 이렇게, map을 사용해서, 객체 하나를 가지고 여러군데서 사용하는 것을, '싱글톤' 이라고 한다. ==> Controller.java에서 map 사용 
		// 우리가 static 해서 직접 만드는 것을 '싱글톤 패턴' 이라고 한다. 
		// Spring은 싱글톤 기반. 
		// 싱글톤 ==> 객체 하나로 계속 쓰므로 메모리 절약 가능. (메모리 할당을 계속 새로 하지 않으니까) 
		
	}
}
class B
{
	public void display()
	{
		System.out.println("display call...");
	}
}
