package kr.or.ddit.board_detail.dao;

import java.util.List;

import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;

public class Board_detailDaoImpl implements IBoard_detailDao {

	@Override
	public List<Board_detailVO> getAllBd(SqlSession openSession) {
		List<Board_detailVO> list =  openSession.selectList("bd.getAllBd");
		return list;
	}

	@Override
	public Board_detailVO selectBd(SqlSession openSession, String board_num) {
		Board_detailVO vo = openSession.selectOne("bd.selectBd",board_num);
		return vo;
	}

	@Override
	public int getBdCnt(SqlSession openSession) {
		int cnt = openSession.selectOne("bd.getBdCnt");
		return cnt;
	}

	@Override
	public int insertBd(SqlSession openSession, Board_detailVO board_detailVo) {
		int cnt = openSession.insert("bd.insertBd",board_detailVo);
		return cnt;
	}

	@Override
	public int updateBd(SqlSession openSession, Board_detailVO board_detailVo) {
		int cnt = openSession.update("bd.updateBd",board_detailVo);
		return cnt;
	}

	@Override
	public int getBdMax(SqlSession openSession) {
		int cnt = openSession.selectOne("bd.getBdMax");
		return cnt;
	}

	@Override
	public List<Board_detailVO> selectBdByBc(SqlSession openSession, String board_code) {
		List<Board_detailVO> list =  openSession.selectList("bd.selectBdByBc",board_code);
		return list;
	}

	@Override
	public List<Board_detailVO> selectBdPagingList(SqlSession openSession,
			PageVO pageVo) {
		List<Board_detailVO> list =  openSession.selectList("bd.selectBdPagingList",pageVo);
		return list;
	}

}
