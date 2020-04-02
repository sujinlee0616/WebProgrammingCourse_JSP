// @RequsetMapping: 메소드가 여러개일 때, 특정 메소드(메소드이름:argument로 받은 String) 찾아오는 애.

package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE, METHOD }) // 클래스(TYPE)과 메소드를 구분하는 애   ==> 이 Annotation은 클래스 또는 메소드 위에서만 사용한다.
public @interface RequestMapping {
	public String value(); // 결과 => aaa
}
