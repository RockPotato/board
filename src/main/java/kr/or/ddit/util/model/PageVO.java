package kr.or.ddit.util.model;

public class PageVO {
		private int page; // 페이지번호
		private int pageSize; // 페이지당 사이즈
		private String board_code;
		public String getBoard_code() {
			return board_code;
		}
		public void setBoard_code(String board_code) {
			this.board_code = board_code;
		}
		public PageVO() {
			
		}
		public PageVO(int page, int pageSize) {
			this.page=page;
			this.pageSize=pageSize;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		@Override
		public String toString() {
			return "pageVo [page=" + page + ", pageSize=" + pageSize + "]";
		}
		

}
