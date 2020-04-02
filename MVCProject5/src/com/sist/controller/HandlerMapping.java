// HandlerMapping: 
package com.sist.controller;
import java.util.*;
import java.io.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/*					 XML의 path를 읽어온다
 * DispatcherServlet ==================> HandlerMapping <==========> XMLParser
 * ================						 				List<String>: package명 
 *  - 톰캣의 시작점은 항상 DispatcherServlet
 *  - 클래스가 여러개면 main 있는 클래스부터 실행하니까!
 *  
 */

public class HandlerMapping {
	private List<String> list = new ArrayList<String>();

	public HandlerMapping(String path, String defaultPath) // argument: from web.xml
	{
		System.out.println("========== 흐름파악: HandlerMapping - HandlerMapping 메소드 (시작) ========== ");
		
		try 
		{
			// 1. [Sax Parser] web.xml 파싱해서 pList에 패키지 명을 담는다 
			SAXParserFactory spf=SAXParserFactory.newInstance();
			SAXParser sp=spf.newSAXParser();
			XMLParser xp=new XMLParser(); // ★XML Parser★
			sp.parse(new File(path), xp); 
			List<String> pList=xp.getList(); // pList에 패키지 명을 담는다. 
			System.out.println("pList="+pList);
			
			// 2. ComponentScan으로 클래스명을 가져와서 list에 클래스명(파일명) 담는다.
			ComponentScan cs = new ComponentScan(); // ★ComponentScan★
			for(String pack:pList)
			{
				List<String> fNames = cs.getClassName(pack,defaultPath); // fName:file names
				for(String name:fNames)
				{
					list.add(name); // 파일명을 list에 담는다 
				}
			}
			
		} catch (Exception ex) {}
		System.out.println("HandlerMapping에서 return 하는 list="+list); 
		
		System.out.println("========== 흐름파악: HandlerMapping - HandlerMapping 메소드 (끝) ========== ");
	}
	
	public List<String> getList() {
		System.out.println("========== 흐름파악: HandlerMapping - getList 메소드 (시작) ========== ");
		return list;
	}
	
}












