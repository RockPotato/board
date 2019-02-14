package kr.or.ddit.board_detail.model;

public class Board_detailVO {
	private String board_num; // 게시글번호
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getDel_check() {
		return del_check;
	}
	public void setDel_check(String del_check) {
		this.del_check = del_check;
	}
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBoard_num2() {
		return board_num2;
	}
	public void setBoard_num2(String board_num2) {
		this.board_num2 = board_num2;
	}
	private String title; // 제목
	private String content; // 내용 
	private String upd_date; // 작성일시
	private String del_check; // 삭제여부
	private String board_code; // 게시판 분류 번호
	private String userid; // 회원 아이디
	private String board_num2; // 부모 게시글 번호
}
