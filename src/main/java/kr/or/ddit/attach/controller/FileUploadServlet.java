package kr.or.ddit.attach.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import kr.or.ddit.board_detail.service.Board_detailServiceImpl;
import kr.or.ddit.board_detail.service.IBoard_detailService;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 5MB = 5 * 1024 * 1024 BYTE
// 5MB = 5 * 1MB
// 1MB = 1024KB
// 1KB = 1024BYTE

@WebServlet("/fileUpload")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class FileUploadServlet extends HttpServlet {
	
	private IBoard_detailService bdService;
	private IAttachService aService;
	
	@Override
	public void init() throws ServletException {
		bdService = new Board_detailServiceImpl();
		aService = new AttachServiceImpl();
	}

	private static final String UPLOAD_PATH = "d:\\picture\\";
	private Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(Integer.parseInt(request.getParameter("cnt"))<5){
			String cnt =Integer.toString(Integer.parseInt(request.getParameter("cnt"))+1);
			request.setAttribute("cnt", cnt);
		}
		else{
			String cnt =request.getParameter("cnt");
			request.setAttribute("cnt", cnt);
		}
		request.setAttribute("board_code",request.getParameter("board_code"));
		request.getRequestDispatcher("/board/boardDetailMake.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String contentType = request.getContentType();
		String board_num = Integer.toString(bdService.getBdMax()+1);
		String uploadFile = request.getParameter("uploadFile");
		String content =request.getParameter("content");
		String cnt = request.getParameter("cnt");
		String title = request.getParameter("tempTitle");
		request.setAttribute("content", content);
		request.setAttribute("title", title);
		
		// System.out.println("userId : " +userId);
		// System.out.println("uploadFile : " +uploadFile);
		List<AttachVO> fileList ;
		if (request.getParameter("fileListSize")==null) {
			fileList = new ArrayList<AttachVO>();
		}else{
			fileList = aService.selectAttachByBn(board_num);
		}
		// low level -->high level
		// 하위 레벨은 상위 레벨을 포함하는 개념
		// 로깅 레벨이 info : logger.info(...), logger.warn(...), logger.error(...)
		// 로깅 레벨 error : logger.error(...)
		// trace, debug, info, warn, error
		logger.debug("contentType : " + contentType);
		logger.debug("board_num : " + board_num);
		logger.debug("uploadFile : " + uploadFile);
		
		// part 정보 확인
//		request.getPart(name);
//		request.getParts();
//		Collection<Part> parts = request.getParts();
//		for (Part part : parts) {
//			logger.debug("partName : {}",part.getName());
//			logger.debug("Content-Disposition : {}",part.getHeader("Content-Disposition"));
//		}
		Part uploadFilePart = request.getPart("uploadFile");
		String contentDisposition = uploadFilePart.getHeader("Content-Disposition");
		logger.debug("contentDisposition : {}",contentDisposition);
		logger.debug("partname : "+uploadFilePart.getName());
//		uploadFilePart.write("d:\\picture\\brown.png");
		
		//application
		//localhost/upload --> 물리적 경로를 확인
		ServletContext application = getServletContext();
		String path = application.getRealPath("/upload");
		logger.debug("path : {}",path);
		
//		// InputStream is = uploadFilePart.getInputStream();
//		ServletInputStream sis = request.getInputStream();
//		InputStreamReader isr = new InputStreamReader(sis);
//		char[] buff = new char[512];
//		int len =0;
//		
//		while((len=isr.read(buff))>-1){
//			for (int i = 0; i < buff.length; i++) {
//				System.out.println(buff[i]);
//			}
//		}
		
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
			logger.debug("hahahah"+filename);
			String realFilename = uuidFilename+"."+filename.substring(filename.lastIndexOf("."));
			uploadFilePart.write(path +File.separator+ realFilename);
			uploadFilePart.delete();
			AttachVO vo = new AttachVO(attach_code, filename, path, board_num,realFilename);
			fileList.add(vo);
			request.setAttribute("fileList",fileList);
		}
		
		doGet(request, response);
	}
}
