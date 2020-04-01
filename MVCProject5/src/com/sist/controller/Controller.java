// @Controller: 
// 역할은 Model인데 이름은 Controller..
package com.sist.controller;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE) // 클래스(TYPE) 구분하는 애 ==> 이 Annotation은 클래스 위에서만 사용한다.
public @interface Controller {

}
