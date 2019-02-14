package kr.or.ddit.board_class.model;

import java.util.Date;

public class Board_classVO {
	private String board_code; // 게시판 분류 번호
	private String board_nm; // 게시판 이름
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getBoard_nm() {
		return board_nm;
	}
	public void setBoard_nm(String board_nm) {
		this.board_nm = board_nm;
	}
	public String getBoard_use() {
		return board_use;
	}
	public void setBoard_use(String board_use) {
		this.board_use = board_use;
	}
	private String board_use; // 사용여부
}
