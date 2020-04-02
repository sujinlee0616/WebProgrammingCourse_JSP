// 2020.04.01 수업 
// Spring에서 <context:component-scan base-package="com.sist.*"/> 이런식으로 package 단위로 등록하는 게 어떻게 가능한지 알아보자.
package com.sist.temp;
import java.util.*;
import java.io.*;	

public class ComponentScan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		componentScan("com.sist.model"); // com.sist.model package에 어떤 컴포넌트가 있는지 출력해준다.
	}

	// Component scan - 특정 패키지(argument로 넣은 package)에 어떤 컴포넌트가 있는지 스캔한다. 
	public static List<String> componentScan(String pack)
	{
		List<String> list = new ArrayList<String>();
		
		try
		{
			String path="C:\\webDev\\webStudy\\MVCProject4\\src";
			path=path+"\\"+pack.replace(".", "\\"); 
			System.out.println("path="+path);
			
			File dir = new File(path);
			File[] files = dir.listFiles();
			for(File f:files)
			{
				// System.out.println(f.getName()); // 파일이름 확인 
				String ext=f.getName().substring(f.getName().lastIndexOf(".")+1);  // 파일명에서 글자 잘라서 확장자를 변수 ext에 저장
				// f.getName(): 파일명 가져옴 ,  ext: extension. 확장자. 
				// System.out.println("ext="+ext); // 확장자 확인 - Java군 
				if(!ext.equals("java")) 
					continue;  // 파일의 확장자가 Java가 아니면 (Java 파일이 아니면) 등록하지 않는다.  
				String p = pack+"."+f.getName().substring(0,f.getName().lastIndexOf("."));
				System.out.println("p="+p); 
				// 그런데, 여기까지 코딩했을 때의 문제점이, 인터페이스나 추상클래스도 등록이 되어버림 
				// 인터페이스/추상클래스는 메모리 할당이 안 되므로 오류남 ==> 이런 애들은 제외시켜야 한다. 어노테이션 쓰면 됨. 
				// 어노테이션은 구분자로 사용됨. 
				// (메모리 할당 요청하는 애 위에만 @Controller 어노테이션 쓰고, 메모리 할당 요청하지 않는 애 위에는 @Controller 어노테이션 쓰지 않으면 됨.) 
				// (참고: VO에는 메모리 할당 안 할거임. VO 빼고 나머지는 다 메모리 할당 한다. ==> @Controller 안 씀)
				// (참고: @Controller, @Repository: 메모리 할당) 
				// 우리는 annotation을 직접 만들어볼 것이다.
				
				// list.add(p);
				// 질문) list.add(p) 해야 하는거 아니에요?
				// 선생님 답변) 응 원래는 그렇게 해야하긴 하는데 이번에는 그냥 이렇게 읽어온다는것만 보려고 코딩한거야. 실제로 어디다가 써먹으려고 코딩한거 아냐.
				// MVCProject5에서는 써먹으려고 list.add(p) 했어 
				
			}
		}catch (Exception ex) {}
		
		// System.out.println(list);
		return list;
	}
}

/*
 * 
 * Spring에서  applicationContext.xml에다가 
 * <context:component-scan base-package="com.sist.*"/>
 * 이런식으로 패키지 단위로 등록할 수 있는 이유는, 
 * 사실은 숨겨져있지만, 위와 같이, component-scan 하는 코드가 어딘가에 짜여져 있기 때문. 
 * 
 * 어제 했던대로 WebContent > WEB-INF > applicationContext.xml 에서 <bean> 등록하는 방식은, 
 * 클래스(<bean>)을 하나하나 등록해야 하다보니 클래스가 많을 경우 번거로움
 * ==> 이 파일과 같이 package 형태로 등록하면 더 효율적으로 일할 수 있음. 
 *     Spring에서도 이런식으로 package 단위로 등록한다. 
 *     (실제코드: applicationContext.xml에 <context:component-scan base-package="com.sist.*"/> 이런식으로 코딩함)  
 *     다만, 라이브러리 불러올 때는 그냥 applicationContext.xml에 <bean>을 하나하나 등록한다.
 *     왜냐면 라이브러리는 'com.sist.*' 이런식으로 '*' 주면 너무 많은 애들이 등록되기 때문...    * 
 * 
 * Spring에는 new가 없음. 결합성이 낮게 짜기 위해서. 
 *  - 기존에 new를 사용하는 방식의 단점) 
 *    B에 aaa메소드가 있는데 이 메소드의 이름을 aaa1로 변경하면, 
 *    A에서 B b = new B(); b.aaa();라고 코딩한게 있으면 에러남.  
 *    ==> Spring에서는 @Autowired 가 메모리 할당해주므로, new 쓰지 않음 
 *    
 */







