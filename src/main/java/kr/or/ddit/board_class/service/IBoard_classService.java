package kr.or.ddit.board_class.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board_class.model.Board_classVO;

public interface IBoard_classService {
	List<Board_classVO> getAllBc();
	public Board_classVO selectBc(String board_code);
	int getBcCnt();
	int insertBc(Board_classVO board_classVo);
	int updateBc(Board_classVO board_classVo);
	int getBcMax();
}
