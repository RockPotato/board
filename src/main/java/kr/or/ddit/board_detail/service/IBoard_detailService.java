package kr.or.ddit.board_detail.service;

import java.util.List;

import kr.or.ddit.board_detail.model.Board_detailVO;

public interface IBoard_detailService {
	List<Board_detailVO> getAllBd();
	public Board_detailVO selectBd(String board_code);
	int getBdCnt();
	int insertBd(Board_detailVO board_detailVo);
	int updateBd(Board_detailVO board_detailVo);
	int getBdMax();
}
