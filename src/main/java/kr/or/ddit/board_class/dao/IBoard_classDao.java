package kr.or.ddit.board_class.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board_class.model.Board_classVO;

public interface IBoard_classDao {
	List<Board_classVO> getAllBc(SqlSession openSession);
}
