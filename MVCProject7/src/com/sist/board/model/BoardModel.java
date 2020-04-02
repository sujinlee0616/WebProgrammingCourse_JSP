package com.sist.board.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.common.model.CommonData;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

@Controller
public class BoardModel {
	@RequestMapping("site/board/list.do")
	public String board_list(HttpServletRequest request, HttpServletResponse response)
	{
		//request.setAttribute("msg", "게시판"); // (key, value)
		List<EmpVO> list = EmpDAO.empAllData();
		request.setAttribute("list", list);
		
		request.setAttribute("main_jsp", "board/list.jsp");
		CommonData.commonData(request);
		return "../main.jsp"; // 주의!! 메인으로 보내야함!!
	}
}
