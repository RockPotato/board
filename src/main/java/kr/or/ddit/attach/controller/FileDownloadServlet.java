package kr.or.ddit.attach.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.attach.service.AttachServiceImpl;
import kr.or.ddit.attach.service.IAttachService;

@WebServlet("/filedownload")
public class FileDownloadServlet extends HttpServlet {
       
	private IAttachService service;

	@Override
	public void init() throws ServletException {
		service = new AttachServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attach_code = request.getParameter("attach_code");
		String board_num = request.getParameter("board_num");
		response.setContentType("application/octect-stream");
		AttachVO vo = new AttachVO();
		vo.setAttach_code(attach_code);
		vo.setBoard_num(board_num);
		AttachVO selectAttach = service.selectAttach(vo);
		FileInputStream fis = new FileInputStream(new File(selectAttach.getAttach_path()+selectAttach.getAttach_realnm()));
		String fileName = new String(selectAttach.getAttach_nm().getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		ServletOutputStream sos = response.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while((len=fis.read(buff))!=-1){
			sos.write(buff);
		}
		sos.close();
		fis.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
