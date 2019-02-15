package kr.or.ddit.board_detail.dao;

import java.util.List;

import kr.or.ddit.board_class.model.Board_classVO;
import kr.or.ddit.board_detail.model.Board_detailVO;

import org.apache.ibatis.session.SqlSession;

public interface IBoard_detailDao {
	List<Board_detailVO> getAllBd(SqlSession openSession);
	public Board_classVO selectBc(SqlSession openSession,String board_code);
	int getBcCnt(SqlSession openSession);
	int insertBc(SqlSession openSession,Board_classVO board_classVo);
	int updateBc(SqlSession openSession,Board_classVO board_classVo);
	int getBcMax(SqlSession openSession);
}
