package com.sist.news;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rss {
	private Channel channel = new Channel();

	public Channel getChannel() {
		return channel;
	}
	
	@XmlElement
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}


/*	<JAXP, JAXB>
 *  1. JAXP: Java API for XML Processing
 *   - 설정 파일을 읽을 때 많이 사용 ex) Spring에서, Mybatis에서, ...
 *    1) DOM Parsing
 *      - DOM: Document Object Model 
 *      - MVCProject3에서 쓴 게 DOM parsing.
 *      - 메모리에 저장. ==> 수정/삭제/추가 가능
 *    2) SAX
 *      - SAX: Simple API for XML
 *      - 단순하게 XML을 읽어옴. 
 *      - 읽기 전용임. 
 *      - Mybatis, Spring은 SAX임.
 *  2. JAXB: Java Architecture for XML Binding ==> Annotation 이용
 *   - XML이 복잡할 때 사용. 빅데이터용. 
 *    1) marshal: Java class에 있는 데이터를 XML로 변환
 *    2) unmarshal: XML을 Java Object로 변환 
 *    https://ukzzang.tistory.com/12
 * 
 */

/*
 *  <rss> // ==> <rss></rss>사이에 태그가 있군! ==> rss는 class
 *  	<channel> // ==> <channel></channel>사이에 태그가 있군! ==> channel은 class
 *  		<item>
 *  			<title></title> // ==> <title></title>사이에는 태그가 아니라 값이 있군! ==> title은 변수임.
 *  			<author></author>
 *  			<description></description>
 *  			<link></link>
 *  		</item>
 *			<item>
 *  			<title></title>
 *  			<author></author>
 *  			<description></description>
 *  			<link></link>
 *  		</item>
 *  	</channel>
 *  </rss>
 *  
 */

/*
 * ex) 네이버 News API  
 * http://newssearch.naver.com/search.naver?where=rss&query=%EC%BD%94%EB%A1%9C%EB%82%98
 * 
 * 
 */



