package kr.or.ddit.board_class.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board_class.dao.Board_classDaoImpl;
import kr.or.ddit.board_class.dao.IBoard_classDao;
import kr.or.ddit.board_class.model.Board_classVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVO;

public class Board_classServiceImpl implements IBoard_classService {

	private IBoard_classDao dao;
	
	public Board_classServiceImpl() {
		dao = new Board_classDaoImpl();
	}

	@Override
	public List<Board_classVO> getAllBc() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<Board_classVO> list =dao.getAllBc(openSession);
		openSession.commit();
		openSession.close();
		return list;
	}

	@Override
	public Board_classVO selectBc(String board_code) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		Board_classVO vo =dao.selectBc(openSession, board_code);
		openSession.commit();
		openSession.close();
		return vo;
	}

	@Override
	public int getBcCnt() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt=dao.getBcCnt(openSession);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int insertBc(Board_classVO board_classVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt=dao.insertBc(openSession, board_classVo);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int updateBc(Board_classVO board_classVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt=dao.updateBc(openSession, board_classVo);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int getBcMax() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt=dao.getBcMax(openSession);
		openSession.commit();
		openSession.close();
		return cnt;
	}

}
