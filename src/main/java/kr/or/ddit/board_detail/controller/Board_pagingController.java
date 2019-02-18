package kr.or.ddit.board_detail.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.board_detail.service.Board_detailServiceImpl;
import kr.or.ddit.board_detail.service.IBoard_detailService;
import kr.or.ddit.util.model.PageVO;

/**
 * Servlet implementation class Board_pagingController
 */
@WebServlet("/boardpaging")
public class Board_pagingController extends HttpServlet {
	private IBoard_detailService service;
	@Override
	public void init() throws ServletException {
		service= new Board_detailServiceImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_code = request.getParameter("board_code");

		// page, pageSize에 해당하는 파라미터 받기 ==> pageVo
		// 단 파라미터가 없을 경우 page : 1, pageSize : 10;
		String pageStr = request.getParameter("page");
		String pagSizeeStr = request.getParameter("pageSize");
		int page = pageStr == null ? 1 : Integer.parseInt(request
				.getParameter("page"));
		int pageSize = pagSizeeStr == null ? 10 : Integer.parseInt(request
				.getParameter("pageSize"));

		PageVO pageVo = new PageVO(page, pageSize);
		pageVo.setBoard_code(board_code);
		// userService 객체를 이용 userPageingList 조회
		Map<String, Object> resultMap = service.selectBdPagingList(pageVo,board_code);
		List<Board_detailVO> bdList = (List<Board_detailVO>) resultMap.get("bdList");
		int bdCnt = (Integer) resultMap.get("getBdCnt");
		// request 객체에 조호된 결과를 속성으로 설정
		request.setAttribute("board_code", board_code);
		request.setAttribute("bdList", bdList);
		request.setAttribute("bdCnt", bdCnt);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		// userPagingList를 화면으로 출력할 jsp로 위임(forward)

		request.getRequestDispatcher(request.getContextPath()+"/board/boardPagingList.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_num = request.getParameter("board_num");
		Board_detailVO selectBd = service.selectBd(board_num);
		selectBd.setDel_check("y");
		service.updateBd(selectBd);
		response.sendRedirect(request.getContextPath()+"/boardpaging?board_code="+selectBd.getBoard_code());
	}

}
