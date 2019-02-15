package kr.or.ddit.login;

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
 * Servlet implementation class mainServlet
 */
@WebServlet("/main")
public class mainServlet extends HttpServlet {
    public mainServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("bcList")!=null){
			session.removeAttribute("bcList");
		}
		IBoard_classService service = new Board_classServiceImpl();
		List<Board_classVO> bcList = service.getAllBc();
		session.setAttribute("bcList", bcList);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
