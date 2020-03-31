// 조건문 없이 처리하기 위해서 클래스 통합, 인터페이스 만들어버림 
package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {
	public String handlerRequest(HttpServletRequest request);
	
}


