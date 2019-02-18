package kr.or.ddit.board_detail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.board_detail.service.Board_detailServiceImpl;
import kr.or.ddit.board_detail.service.IBoard_detailService;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/bdForm")
public class Board_detailMakeController extends HttpServlet {
	private IBoard_detailService service;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_code = request.getParameter("board_code");
		String board_num2 = request.getParameter("board_num2");
		if(board_num2!=null){
			request.setAttribute("board_num2", board_num2);
		}
		request.setAttribute("board_code", board_code);
		request.getRequestDispatcher(request.getContextPath()+"/board/boardDetailMake.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=false;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String board_code = request.getParameter("board_code");
		String board_num2 = request.getParameter("board_num2");
		String content = request.getParameter("smarteditor");
		String title = request.getParameter("title");
		String board_num = request.getParameter("board_num");
		UserVO userVo = (UserVO) request.getSession().getAttribute("userVo");
		String userId = userVo.getUserId();
		if(board_num==null){
			board_num =Integer.toString(service.getBdMax()+1);
			flag=true;
		}
		Board_detailVO vo = new Board_detailVO(board_num, title, content, board_code, userId, board_num2);
		int cnt = 0;
		if(flag){
			cnt = service.insertBd(vo);
		}
		else{
			cnt = service.updateBd(vo);
		}
		if(cnt>0){
			response.sendRedirect("/boarddetail?board_num="+board_num);
		}
	}

	@Override
	public void init() throws ServletException {
		service = new Board_detailServiceImpl();
	}
	
	

}
