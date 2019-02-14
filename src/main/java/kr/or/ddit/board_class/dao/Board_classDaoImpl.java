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

}
