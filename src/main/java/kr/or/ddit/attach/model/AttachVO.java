package kr.or.ddit.attach.model;

import java.util.Date;

public class AttachVO {
	private String attached_code; // 첨부파일 번호
	private String attached_nm; // 업로드파일명
	private String attached_path; // 저장경로
	private String board_num; // 게시글 번호
	public String getAttached_code() {
		return attached_code;
	}
	public void setAttached_code(String attached_code) {
		this.attached_code = attached_code;
	}
	public String getAttached_nm() {
		return attached_nm;
	}
	public void setAttached_nm(String attached_nm) {
		this.attached_nm = attached_nm;
	}
	public String getAttached_path() {
		return attached_path;
	}
	public void setAttached_path(String attached_path) {
		this.attached_path = attached_path;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	}
