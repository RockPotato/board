package kr.or.ddit.board_class.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board_class.model.Board_classVO;

public class Board_classDaoImpl implements IBoard_classDao {

	@Override
	public List<Board_classVO> getAllBc(SqlSession openSession) {
		List<Board_classVO> selectList = openSession.selectList("bc.getAllBc");
		return selectList;
	}

	@Override
	public Board_classVO selectBc(SqlSession openSession, String board_code) {
		Board_classVO vo = openSession.selectOne("bc.selectBc", board_code);
		return vo;
	}

	@Override
	public int getBcCnt(SqlSession openSession) {
		int cnt = openSession.selectOne("bc.getBcCnt");
		return cnt;
	}

	@Override
	public int insertBc(SqlSession openSession, Board_classVO board_classVo) {
		int cnt = openSession.insert("bc.insertBc",board_classVo);
		return cnt;
	}

	@Override
	public int updateBc(SqlSession openSession, Board_classVO board_classVo) {
		int cnt = openSession.update("bc.updateBc",board_classVo);
		return cnt;
	}

	@Override
	public int getBcMax(SqlSession openSession) {
		int cnt = openSession.selectOne("bc.getBcMax");
		return cnt;
	}

}
