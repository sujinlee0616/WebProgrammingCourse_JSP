package com.sist.model;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
/* 
 * Controller => 요청을 받는다, 처리 결과값을 JSP로 전송 
 * Model => 처리한다. (ex. DB연동...) 
 * View => 처리 결과값을 화면에 출력.
 * 		request   			request			request				request          
 * 사용자 =======> Controller =======> Model =======> Controller =======> View
 * 		  요청													forward
 */
public class ListModel {
	public void execute(HttpServletRequest request)
	{
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");
		
		request.setAttribute("list", list);
		// Model(본 파일)은  Controller로 데이터 전송 => Controller는 (forward해서)JSP로 다시 전송
		
		
	}
}




