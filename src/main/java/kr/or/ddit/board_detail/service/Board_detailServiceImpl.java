package kr.or.ddit.board_detail.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board_class.model.Board_classVO;
import kr.or.ddit.board_detail.dao.Board_detailDaoImpl;
import kr.or.ddit.board_detail.dao.IBoard_detailDao;
import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class Board_detailServiceImpl implements IBoard_detailService {

	private IBoard_detailDao dao;
	public Board_detailServiceImpl() {
		dao =new Board_detailDaoImpl();
	}
	
	@Override
	public List<Board_detailVO> getAllBd() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<Board_detailVO> list =dao.getAllBd(openSession);
		openSession.commit();
		openSession.close();
		return list;
	}

	@Override
	public Board_detailVO selectBd(String board_code) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		Board_detailVO vo =dao.selectBd(openSession, board_code);
		openSession.commit();
		openSession.close();
		return vo;
	}

	@Override
	public int getBdCnt() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt = dao.getBdCnt(openSession);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int insertBd(Board_detailVO board_detailVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt = dao.insertBd(openSession, board_detailVo);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int updateBd(Board_detailVO board_detailVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt = dao.updateBd(openSession, board_detailVo);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int getBdMax() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt = dao.getBdMax(openSession);
		openSession.commit();
		openSession.close();
		return cnt;
	}

}
