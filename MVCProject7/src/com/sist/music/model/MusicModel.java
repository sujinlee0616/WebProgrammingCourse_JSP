package com.sist.music.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MusicModel {
	@RequestMapping("site/music/music.do")
	public String music_list(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "music/music.jsp");
		request.setAttribute("msg", "음악");
		CommonData.commonData(request);
		return "../main.jsp";
	}
}
