package kr.or.ddit.reply.model;

public class ReplyVO {
	private String reply_code; // 댓글번호
	private String reply_content; // 댓글내용
	private String reply_del; // 댓글 삭제 여부
	private String reply_date; // 댓글작성일시
	private String board_num; // 게시글번호
	private String userid; // 회원아이디
	public String getReply_code() {
		return reply_code;
	}
	public void setReply_code(String reply_code) {
		this.reply_code = reply_code;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_del() {
		return reply_del;
	}
	public void setReply_del(String reply_del) {
		this.reply_del = reply_del;
	}
	public ReplyVO() {
	}
	public ReplyVO(String reply_code, String reply_content, String board_num,
			String userid) {
		super();
		this.reply_code = reply_code;
		this.reply_content = reply_content;
		this.board_num = board_num;
		this.userid = userid;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
