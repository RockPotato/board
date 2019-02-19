package kr.or.ddit.attach.model;


public class AttachVO {
	@Override
	public String toString() {
		return "AttachVO [attach_code=" + attach_code + ", attach_nm="
				+ attach_nm + ", attach_path=" + attach_path + ", board_num="
				+ board_num + ", attach_realnm=" + attach_realnm + "]";
	}
	private String attach_code; // 첨부파일 번호
	private String attach_nm; // 업로드파일명
	private String attach_path; // 저장경로
	private String board_num; // 게시글 번호
	private String attach_realnm; // 실제파일명
	public String getAttach_realnm() {
		return attach_realnm;
	}
	public void setAttach_realnm(String attach_realnm) {
		this.attach_realnm = attach_realnm;
	}
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
			String board_num, String attach_realnm) {
		super();
		this.attach_code = attach_code;
		this.attach_nm = attach_nm;
		this.attach_path = attach_path;
		this.board_num = board_num;
		this.attach_realnm = attach_realnm;
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
