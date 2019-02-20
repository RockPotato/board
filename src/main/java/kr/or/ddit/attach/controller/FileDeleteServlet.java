package kr.or.ddit.attach.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.user.model.UserVO;

/**
 * Servlet implementation class FileDeleteServlet
 */
@WebServlet("/filedelete")
public class FileDeleteServlet extends HttpServlet {
	private IAttachService service;
	@Override
	public void init() throws ServletException {
		service = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String board_code = request.getParameter("board_code");
		String board_num2 = request.getParameter("board_num2");
		String content = request.getParameter("smarteditor");
		String title = request.getParameter("title");
		String board_num = request.getParameter("board_num");
		String attach_code = request.getParameter("attachCode");
		UserVO userVo = (UserVO) request.getSession().getAttribute("userVo");
		String userId = userVo.getUserId();
		AttachVO attachVO = new AttachVO();
		attachVO.setAttach_code(attach_code);
		attachVO.setBoard_num(board_num);
		service.deleteAttach(attachVO);
		Board_detailVO vo = new Board_detailVO(board_num, title, content, board_code, userId, board_num2); 
		request.setAttribute("boardVo", vo);
		response.sendRedirect("/boardupd?board_code="+board_code+"&board_num2="
								+board_num2+"&board_num="+board_num);
	}

}
