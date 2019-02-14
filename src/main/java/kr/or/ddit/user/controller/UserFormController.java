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

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.util.PartUtil;

@WebServlet("/userForm")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UserFormController extends HttpServlet {

	private IUserService service;

	/**
	 * Method : doGet 작성자 : PC04 변경이력 :
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 *             Method 설명 : 사용자 등록 폼
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/user/userForm.jsp").forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		service = new UserServiceImpl();
	}

	/**
	 * Method : doPost 작성자 : PC04 변경이력 :
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 *             Method 설명 : 사용자 정보 등록
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("UTF-8");

		// 1. 사용자 아이디 중복체크
		String userId = (String) req.getParameter("userId");
		UserVO selectUser = service.selectUser(userId);

		// 2-1. 중복체크 통과 : 사용자 정보를 db에 입력
		// 2-1-1. 사용자 페이징 리스트 1페이지로 이동
		if (selectUser == null) {
			UserVO addVo = new UserVO();
			addVo.setUserId(userId);
			addVo.setAddr1((String) req.getParameter("addr1"));
			addVo.setAddr2((String) req.getParameter("addr2"));
			addVo.setAlias((String) req.getParameter("alias"));
			addVo.setPass((String) req.getParameter("pass"));
			addVo.setUserNm((String) req.getParameter("userNm"));
			addVo.setZipcode((String) req.getParameter("zipcode"));

			// 사용자 사진
			Part profilePart = req.getPart("profile");
			String filename = "";
			String realFileName = "";
			// 사용자가 사진을 올린 경우
			if (profilePart.getSize() > 0) {
				// 사용자 테이블 파일명을 저장(실제 업로드한 파일명, 파일 충돌을 방지하기 위해 사용한 uuid)
				String contentDisposition = profilePart
						.getHeader("Content-Disposition");
				filename = PartUtil
						.getFileNameFromPart(contentDisposition);
				realFileName = "d:\\picture\\" +UUID.randomUUID().toString();
				
				// 디스크에 기록 (d:\picture\ +realFileName)
				profilePart.write(realFileName);
				profilePart.delete();
			}
			// 사용자가 사진을 올리지 않은 경우
			addVo.setFilename(filename);
			addVo.setRealFilename(realFileName);

			int insertUser = service.insertUser(addVo);

			// 정상입력(성공)
			if (insertUser != 0) {
				// req.getRequestDispatcher("/userPagingList").forward(req,resp);
				req.getSession().setAttribute("msg", "정상 등록 되었습니다.");
				resp.sendRedirect(req.getContextPath() + "/userPagingList");
			}

			// 정상입력(실패)
			else {
				doGet(req, resp);
			}
		}
		// 2-2. 중복체크 통과 실패 : 사용자 등록페이지로 이동
		else {
			// forward 시에는 최초 요청한 method를 변경불가
			// post --> post
			// req.getRequestDispatcher("/userForm").forward(req,resp);
			req.setAttribute("msg", "중복체크에 실패 했습니다.");
			doGet(req, resp);
		}

	}

}
