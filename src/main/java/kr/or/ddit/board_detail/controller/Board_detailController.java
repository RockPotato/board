package kr.or.ddit.board_detail.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.board_detail.service.Board_detailServiceImpl;
import kr.or.ddit.board_detail.service.IBoard_detailService;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@WebServlet("/boarddetail")
public class Board_detailController extends HttpServlet {
	private IBoard_detailService service;
	private IReplyService replyService;
	private IAttachService aService;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String board_num = request.getParameter("board_num");
		Board_detailVO selectBd = service.selectBd(board_num);
		List<ReplyVO> selectReplyByBn = replyService.selectReplyByBn(board_num);
		List<AttachVO> selectAttachByBn = aService.selectAttachByBn(board_num);
		request.setAttribute("attachList", selectAttachByBn);
		request.setAttribute("replyList", selectReplyByBn);
		request.setAttribute("selectBd", selectBd);
		request.getRequestDispatcher("/board/boardDetail.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String replyContent = request.getParameter("newReply");
		
		String board_num = request.getParameter("board_num");
		System.out.println(board_num);
		UserVO userVo = (UserVO) request.getSession().getAttribute("userVo");
		String userid = userVo.getUserId();
		String reply_code = Integer.toString(replyService.getReplyMax()+1);
		ReplyVO vo = new ReplyVO(reply_code, replyContent, board_num, userid);
		int cnt = replyService.insertReply(vo);
		if(cnt >0){
			System.out.println("성공");
			doGet(request, response);
		}
	}

	@Override
	public void init() throws ServletException {
		service= new Board_detailServiceImpl();
		replyService = new ReplyServiceImpl();
		aService = new AttachServiceImpl();
	}

}
