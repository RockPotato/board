package kr.or.ddit.reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;

/**
 * Servlet implementation class ReplyUpdController
 */
@WebServlet("/replyupd")
public class ReplyUpdController extends HttpServlet {
    private IReplyService service;
	@Override
	public void init() throws ServletException {
		service = new ReplyServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_num = request.getParameter("board_num");
		String reply_code = request.getParameter("reply_code");
		ReplyVO selectReply = service.selectReply(reply_code);
		selectReply.setReply_del("y");
		service.updateReply(selectReply);
		response.sendRedirect(request.getContextPath()+"/boarddetail?board_num="+board_num);
	}

}
