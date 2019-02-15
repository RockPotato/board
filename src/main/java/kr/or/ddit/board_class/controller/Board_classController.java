package kr.or.ddit.board_class.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board_class.model.Board_classVO;
import kr.or.ddit.board_class.service.Board_classServiceImpl;
import kr.or.ddit.board_class.service.IBoard_classService;

/**
 * Servlet implementation class Board_classController
 */
@WebServlet("/boardclass")
public class Board_classController extends HttpServlet {

	private IBoard_classService service;
	
	
	
	@Override
	public void init() throws ServletException {
		service = new Board_classServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bcMax;
		if(service.getBcMax()!=0){
			bcMax = Integer.toString(service.getBcMax()+1);
		}else{
			bcMax = "0";
		}
		String newBoardNm = request.getParameter("newBoardNm");
		String newBoardUse;
		if(request.getParameter("newBoardUse").equals("사용")){
			newBoardUse="y";
		}else{
			newBoardUse="n";
		}
		Board_classVO newVo = new Board_classVO(bcMax,newBoardNm,newBoardUse);
		int cnt = service.insertBc(newVo);
		if(cnt>0){
			HttpSession session = request.getSession();
			IBoard_classService service = new Board_classServiceImpl();
			List<Board_classVO> bcList = service.getAllBc();
			session.setAttribute("bcList", bcList);
			request.setAttribute("cnt",	cnt);
			System.out.println("haha"+cnt);
			request.getRequestDispatcher(request.getContextPath()+"/main").forward(request, response);
		}
		else{
			request.getRequestDispatcher(request.getContextPath()+"/boardclass").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String board_code = request.getParameter("updBoardCode");
		String updBoardNm = request.getParameter("updBoardNm");
		String updBoardUse=request.getParameter("updBoardUse");
		
		if(updBoardUse.equals("사용")){
			updBoardUse="y";
		}else{
			updBoardUse="n";
		}
		Board_classVO vo = new Board_classVO(board_code, updBoardNm, updBoardUse);
		int cnt = service.updateBc(vo);
		if(cnt>0){
			HttpSession session = request.getSession();
			IBoard_classService service = new Board_classServiceImpl();
			List<Board_classVO> bcList = service.getAllBc();
			session.setAttribute("bcList", bcList);
			request.setAttribute("cnt",	cnt);
			request.getRequestDispatcher(request.getContextPath()+"/main.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher(request.getContextPath()+"/boardclass").forward(request, response);
		}
		
	}

}
