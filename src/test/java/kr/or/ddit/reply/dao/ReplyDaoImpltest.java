package kr.or.ddit.reply.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.reply.model.ReplyVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReplyDaoImpltest {
	private SqlSession openSession;
	private IReplyDao dao;

	@Before
	public void setup() {
		dao = new ReplyDaoImpl();
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory
				.getSqlSessionFactory();
		openSession = sqlSessionFactory.openSession();
	}

	@After
	public void tearDown() {
		openSession.commit();
		openSession.close();
	}

	@Test
	public void testSelectReplyByBn() {
		String board_num = "200";
		List<ReplyVO> selectReplyByBn = dao.selectReplyByBn(openSession, board_num);
		assertEquals(3,selectReplyByBn.size());
	}
	@Test
	public void testGetReplyMax() {
		int ReplyCnt = dao.getReplyMax(openSession);
		assertEquals(11,ReplyCnt);
	}
	@Test
	public void testInsertReply() {
		ReplyVO ReplyVO = new ReplyVO("11", "취하고싶다", "54", "lee");
		int insertReply = dao.insertReply(openSession, ReplyVO);
		assertNotNull(insertReply);
	}
	@Test
	public void testUpdateReply() {
		ReplyVO ReplyVO = new ReplyVO("11", "취하고싶다 비우고 싶다", "54", "lee");
		int updateReply = dao.updateReply(openSession, ReplyVO);
		assertNotNull(updateReply);
	}
	@Test
	public void testSelectReply(){
		String reply_code = "12";
		ReplyVO selectReply = dao.selectReply(openSession, reply_code);
		assertNotNull(selectReply);
	}

}
