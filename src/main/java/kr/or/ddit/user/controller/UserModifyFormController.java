package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.PartUtil;

@WebServlet("/userModifyForm")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UserModifyFormController extends HttpServlet {
	private IUserService service;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		IUserService service = new UserServiceImpl();
		UserVO selectUser = service.selectUser(userId);
		if(selectUser==null){
			response.sendRedirect("/userPagingList");
		}
		System.out.println(selectUser.getAddr1());
		request.setAttribute("userVo", selectUser);
		request.getRequestDispatcher("/user/userModify.jsp").forward(request,
				response);
	}

	private Logger logger = LoggerFactory
			.getLogger(UserModifyFormController.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("UTF-8");

		// 1. 사용자 아이디 중복체크

		// 2-1. 중복체크 통과 : 사용자 정보를 db에 입력
		// 2-1-1. 사용자 페이징 리스트 1페이지로 이동
		String userId = (String) req.getParameter("userId");
		UserVO fileVO = service.selectUser(userId);
		String filename = fileVO.getFilename();
		String realFilename = fileVO.getRealFilename();
		
		UserVO modifyVo = new UserVO();
		modifyVo.setUserId(userId);
		modifyVo.setAddr1((String) req.getParameter("addr1"));
		modifyVo.setAddr2((String) req.getParameter("addr2"));
		modifyVo.setAlias((String) req.getParameter("alias"));
		modifyVo.setPass((String) req.getParameter("pass"));
		modifyVo.setUserNm((String) req.getParameter("userNm"));
		modifyVo.setZipcode((String) req.getParameter("zipcode"));
		
		Part profilePart = req.getPart("profile");
		if(profilePart.getSize()>0){
			String contentDisposition = profilePart
					.getHeader("Content-Disposition");
			filename = PartUtil
					.getFileNameFromPart(contentDisposition);
			realFilename = "d:\\picture\\" +UUID.randomUUID().toString();
			// 디스크에 기록 (d:\picture\ +realFileName)
			profilePart.write(realFilename);
			profilePart.delete();
		}
		logger.debug(filename,realFilename);
		modifyVo.setFilename(filename);
		modifyVo.setRealFilename(realFilename);
		
		int updateUser = service.updateUser(modifyVo);

		// 정상입력(성공)
		if (updateUser != 0) {
			// req.getRequestDispatcher("/userPagingList").forward(req,resp);
			resp.sendRedirect(req.getContextPath() + "/user?userId="+userId);
		}

		// 정상입력(실패)
		else {
			doGet(req, resp);
		}
		// 2-2. 중복체크 통과 실패 : 사용자 등록페이지로 이동
		// forward 시에는 최초 요청한 method를 변경불가
		// post --> post
		// req.getRequestDispatcher("/userForm").forward(req,resp);
	}

	@Override
	public void init() throws ServletException {
		service = new UserServiceImpl();
	}

}
