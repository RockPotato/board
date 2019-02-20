package kr.or.ddit.board_detail.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.board_detail.service.Board_detailServiceImpl;
import kr.or.ddit.board_detail.service.IBoard_detailService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@WebServlet("/boardupd")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Board_detailUpdController extends HttpServlet {

	private IBoard_detailService bdService;
	private IAttachService aService;

	private static final String UPLOAD_PATH = "d:\\picture\\";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String board_num = req.getParameter("board_num");
		Board_detailVO selectBd = (Board_detailVO) req.getAttribute("boardVo");
		List<AttachVO> selectAttachByBn = aService.selectAttachByBn(board_num);
		if (selectBd == null) {
			selectBd = bdService.selectBd(board_num);
		}
		req.setAttribute("boardVo", selectBd);
		req.setAttribute("attachList", selectAttachByBn);
		req.getRequestDispatcher(
				req.getContextPath() + "/board/boardDetailUpd.jsp").forward(
				req, resp);
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
		if (req.getParts().size() > 0) {
			fileIns(req, resp, board_num);
		}
		UserVO userVo = (UserVO) req.getSession().getAttribute("userVo");
		String userId = userVo.getUserId();
		Board_detailVO vo = new Board_detailVO(board_num, title, content,
				board_code, userId, board_num2);
		int cnt = bdService.updateBd(vo);
		if (cnt > 0) {
			resp.sendRedirect("/boarddetail?board_num=" + board_num);
		}
	}

	private void fileIns(HttpServletRequest request,
			HttpServletResponse response, String board_num) throws IOException,
			ServletException {
		Collection<Part> parts = request.getParts();
		for (Part uploadFilePart:parts) {
			if (uploadFilePart == null) {
				continue;
			}
			String contentDisposition = uploadFilePart
					.getHeader("Content-Disposition");
			if (uploadFilePart.getSize() > 0) {
				String attach_code = "";
				if (aService.getAttachMax(board_num) == 0) {
					attach_code = "1";
				} else {
					attach_code = Integer.toString(aService
							.getAttachMax(board_num) + 1);
				}
				// 첨부파일 파일명
				String filename = PartUtil
						.getFileNameFromPart(contentDisposition);
				if(filename==null){
					continue;
				}
				String uuidFilename = UUID.randomUUID().toString();
				String realFilename = uuidFilename + "."
						+ filename.substring(filename.lastIndexOf("."));
				uploadFilePart.write(UPLOAD_PATH + File.separator
						+ realFilename);
				uploadFilePart.delete();
				AttachVO vo = new AttachVO(attach_code, filename, UPLOAD_PATH,
						board_num, realFilename);
				aService.insertAttach(vo);
			}
		}
	}

	@Override
	public void init() throws ServletException {
		bdService = new Board_detailServiceImpl();
		aService = new AttachServiceImpl();
	}

}
