package kr.or.ddit.board_detail.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;
import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.board_detail.service.Board_detailServiceImpl;
import kr.or.ddit.board_detail.service.IBoard_detailService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@WebServlet("/bdForm")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Board_detailMakeController extends HttpServlet {
	private IBoard_detailService bdService;
	private IAttachService aService;
	private static final String UPLOAD_PATH = "d:\\picture\\";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_code = request.getParameter("board_code");
		String board_num2 = request.getParameter("board_num2");
		String cnt = request.getParameter("cnt");
		if(board_num2!=null){
			request.setAttribute("board_num2", board_num2);
		}
		request.setAttribute("board_code", board_code);
		request.setAttribute("cnt", cnt);
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
			board_num =Integer.toString(bdService.getBdMax()+1);
			flag=true;
		}
		Board_detailVO vo = new Board_detailVO(board_num, title, content, board_code, userId, board_num2);
		int cnt = 0;
		if(flag){
			cnt = bdService.insertBd(vo);
			fileIns(request,response,board_num);
		}
		else{
			cnt = bdService.updateBd(vo);
		}
		if(cnt>0){
			response.sendRedirect("/boarddetail?board_num="+board_num);
		}
	}

	private void fileIns(HttpServletRequest request,
			HttpServletResponse response, String board_num) throws IOException, ServletException {
		int  cnt = Integer.parseInt(request.getParameter("cnt"));
		if(cnt>0){
			for (int i = 1; i <= cnt; i++) {
				Part uploadFilePart = request.getPart("uploadFile"+i+"");
				if(uploadFilePart==null){
					continue;
				}
				String contentDisposition = uploadFilePart.getHeader("Content-Disposition");
				if(uploadFilePart.getSize() >0){
					String attach_code="";
					if(aService.getAttachMax(board_num)==0){
						attach_code="1";
					}
					else{
						attach_code=Integer.toString(aService.getAttachMax(board_num)+1);
					}
					// 첨부파일 파일명
					String filename = PartUtil.getFileNameFromPart(contentDisposition);
					String uuidFilename = UUID.randomUUID().toString();
					String realFilename = uuidFilename+"."+filename.substring(filename.lastIndexOf("."));
					uploadFilePart.write(UPLOAD_PATH +File.separator+ realFilename);
					uploadFilePart.delete();
					AttachVO vo = new AttachVO(attach_code, filename, UPLOAD_PATH, board_num,realFilename);
					aService.insertAttach(vo);
				}
			}
		}
	}

	@Override
	public void init() throws ServletException {
		bdService = new Board_detailServiceImpl();
		aService = new AttachServiceImpl();
	}
	
	

}
