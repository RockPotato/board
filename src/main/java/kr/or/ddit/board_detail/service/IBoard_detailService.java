package kr.or.ddit.board_detail.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.util.model.PageVO;

public interface IBoard_detailService {
	List<Board_detailVO> getAllBd();
	public Board_detailVO selectBd(String board_num);
	Map<String,Object> selectBdPagingList(PageVO pageVo);
	public List<Board_detailVO> selectBdByBc(String board_code);
	int getBdCnt();
	int insertBd(Board_detailVO board_detailVo);
	int updateBd(Board_detailVO board_detailVo);
	int getBdMax();
}
