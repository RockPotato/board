package kr.or.ddit.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.dao.ReplyDaoImpl;
import kr.or.ddit.reply.model.ReplyVO;

public class ReplyServiceImpl implements IReplyService{

	private IReplyDao dao;
	
	
	public ReplyServiceImpl() {
		dao = new ReplyDaoImpl();
	}

	@Override
	public List<ReplyVO> selectReplyByBn(String board_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<ReplyVO> list =dao.selectReplyByBn(openSession,board_num);
		openSession.commit();
		openSession.close();
		return list;
	}

	@Override
	public int insertReply(ReplyVO ReplyVO) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt = dao.insertReply(openSession, ReplyVO);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int updateReply(ReplyVO ReplyVO) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt = dao.updateReply(openSession, ReplyVO);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public int getReplyMax() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int cnt = dao.getReplyMax(openSession);
		openSession.commit();
		openSession.close();
		return cnt;
	}

	@Override
	public ReplyVO selectReply(String reply_code) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		ReplyVO selectReply = dao.selectReply(openSession, reply_code);
		openSession.commit();
		openSession.close();
		return selectReply;
	}

}
