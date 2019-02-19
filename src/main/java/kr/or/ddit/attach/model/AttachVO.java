package kr.or.ddit.attach.model;

import java.util.Date;

public class AttachVO {
	private String attach_code; // 첨부파일 번호
	private String attach_nm; // 업로드파일명
	private String attach_path; // 저장경로
	private String board_num; // 게시글 번호
	public AttachVO() {
	}
	public String getAttach_code() {
		return attach_code;
	}
	public void setAttach_code(String attach_code) {
		this.attach_code = attach_code;
	}
	public String getAttach_nm() {
		return attach_nm;
	}
	public void setAttach_nm(String attach_nm) {
		this.attach_nm = attach_nm;
	}
	public AttachVO(String attach_code, String attach_nm, String attach_path,
			String board_num) {
		super();
		this.attach_code = attach_code;
		this.attach_nm = attach_nm;
		this.attach_path = attach_path;
		this.board_num = board_num;
	}
	public String getAttach_path() {
		return attach_path;
	}
	public void setAttach_path(String attach_path) {
		this.attach_path = attach_path;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	}
