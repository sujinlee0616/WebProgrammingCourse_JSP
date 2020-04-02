// XML Parser: applicationContext.xml에서 패키지를 읽는다.
package com.sist.controller;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler{
	private List<String> list=new ArrayList<String>();

	// 아무 기능도 없던 DefaultHandler를 재정의해서 가져옴
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		System.out.println("========== 흐름파악: XML Parser - startElement (시작) ========== ");
		try {
			// XML에서 <context:component-scan>을 읽었다면
			if(qName.equals("context:component-scan")) // qName: XML 열린 태그의 이름.
			{
				String pack=attributes.getValue("base-package"); // attribute: XML 속성.
				// base-package 속성의 값을 가져와서 pack 변수에 넣어라
				System.out.println("pack="+pack);
				list.add(pack);
			}
		} catch (Exception ex) {}
		System.out.println("========== 흐름파악: XML Parser - startElement (끝) ========== ");
	}
	
	
	public List<String> getList() {
		System.out.println("========== 흐름파악: XML Parser - getList (시작) ========== ");
		return list;
	}

	// 패키지 여러개 쓸 수도 있으니까 list로 가져왔음 
	// (<context:component-scan base-package:"com.sist.A"/> 
	// <context:component-scan base-package:"com.sist.B"/>  이런식으로 package 여러개 쓸 수도 있잖아...) 
	
	
}


/*
 * <SAX Parsing>
 * - XML 파싱 방법 중 하나. 읽기 전용. 
 * - Spring이나 MyBatis에 사용됨
 * - ex) 예시 
 *   <?xml version="1.0" ?> ==> startDocument
 * 	 <book>  ==> startElement
 * 		<list>  ==> startElement 
 * 			<author> ==> startElement 
 * 				박대기 기자  ==> characters
 * 			</author> ==> endElement 
 * 			<title>  ==> startElement 
 * 				오늘은 완연한 봄날씨입니다   ==> characters
 * 			</title>  ==> endElement 
 * 		</list> ==> endElement 
 *   </book> ==> endElement   
 *   ==> endDocument
 *   예시 설명)
 *    - SAX 파싱은 XML을 한 줄씩 읽으면서 처리... (<==> DOM 파싱은 이진트리 구조로 처리) 
 *    - 태그가 열리면 startElement 호출
 *    - 태그가 닫히면 endElement 호출
 */




