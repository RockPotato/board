package kr.or.ddit.board_detail.dao;

import java.util.List;

import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;

public interface IBoard_detailDao {
	List<Board_detailVO> getAllBd(SqlSession openSession);
	public Board_detailVO selectBd(SqlSession openSession,String board_num);
	public List<Board_detailVO> selectBdByBc(SqlSession openSession,String board_code);
	int getBdCnt(SqlSession openSession);
	int insertBd(SqlSession openSession,Board_detailVO board_detailVo);
	int updateBd(SqlSession openSession,Board_detailVO board_detailVo);
	int getBdMax(SqlSession openSession);
	List<Board_detailVO> selectBdPagingList(SqlSession openSession,PageVO pageVo);
}
