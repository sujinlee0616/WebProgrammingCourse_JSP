package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class UpdateModel {
	public void execute(HttpServletRequest request)
	{
		request.setAttribute("msg", "게시판 수정하기");
	}
}
