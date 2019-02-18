package kr.or.ddit.board_detail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.board_detail.service.Board_detailServiceImpl;
import kr.or.ddit.board_detail.service.IBoard_detailService;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/boardupd")
public class Board_detailUpdController extends HttpServlet {

	private IBoard_detailService service; 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String board_num = req.getParameter("board_num");
		Board_detailVO selectBd = service.selectBd(board_num);
		req.setAttribute("boardVo", selectBd);
		req.getRequestDispatcher(req.getContextPath()+"/board/boardDetailUpd.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String board_code = req.getParameter("board_code");
		String board_num2 = req.getParameter("board_num2");
		String content = req.getParameter("smarteditor");
		String title = req.getParameter("title");
		String board_num = req.getParameter("board_num");
		UserVO userVo = (UserVO) req.getSession().getAttribute("userVo");
		String userId = userVo.getUserId();
		Board_detailVO vo = new Board_detailVO(board_num, title, content, board_code, userId, board_num2);
		int cnt =  service.updateBd(vo);
		if(cnt>0){
			resp.sendRedirect("/boarddetail?board_num="+board_num);
		}
	}

	@Override
	public void init() throws ServletException {
		service = new Board_detailServiceImpl();
	}

}
