package kr.or.ddit.attach.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.attach.dao.AttachDaoImpl;
import kr.or.ddit.attach.dao.IAttachDao;
import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.board_class.model.Board_classVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class AttachServiceImpl implements IAttachService {
	
	private IAttachDao dao;
	
	public AttachServiceImpl() {
		dao = new AttachDaoImpl();
	}

	@Override
	public List<AttachVO> getAllAttach() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<AttachVO> list =dao.getAllAttach(openSession);
		openSession.commit();
		openSession.close();
		return list;
	}


	@Override
	public AttachVO selectAttach(AttachVO vo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		AttachVO avo =dao.selectAttach(openSession, vo);
		openSession.commit();
		openSession.close();
		return avo;
	}

	@Override
	public int getAttachCnt() {
		
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int attachCnt = dao.getAttachCnt(openSession);
		openSession.commit();
		openSession.close();
		return attachCnt;
	}

	@Override
	public int insertAttach(AttachVO attachVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int insertAttach = dao.insertAttach(openSession, attachVo);
		openSession.commit();
		openSession.close();
		return insertAttach;
	}

	@Override
	public int updateAttach(AttachVO attachVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int updateAttach = dao.updateAttach(openSession, attachVo);
		openSession.commit();
		openSession.close();
		return updateAttach;
	}

	@Override
	public int getAttachMax(String board_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int attachMax = dao.getAttachMax(openSession,board_num);
		openSession.commit();
		openSession.close();
		return attachMax;
	}

	@Override
	public List<AttachVO> selectAttachByBn(String board_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<AttachVO> selectAttachByBn = dao.selectAttachByBn(openSession, board_num);
		openSession.commit();
		openSession.close();
		return selectAttachByBn;
	}

	@Override
	public int deleteAttach(AttachVO attachVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int deleteAttach = dao.deleteAttach(openSession, attachVo);
		openSession.commit();
		openSession.close();
		return deleteAttach;
	}

}
