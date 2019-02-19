package kr.or.ddit.attach.dao;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.board_class.dao.Board_classDaoImpl;
import kr.or.ddit.board_class.dao.IBoard_classDao;
import kr.or.ddit.board_class.model.Board_classVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AttachDaoImpltest {
	private SqlSession openSession;
	private IAttachDao dao;

	@Before
	public void setup() {
		dao = new AttachDaoImpl();
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
	public void testGetAllAttach() {
		List<AttachVO> allAttach = dao.getAllAttach(openSession);
		assertNotNull(allAttach);
	}
	@Test
	public void testSelectAttach() {
		String attach_code = "1";
		AttachVO selectAttach = dao.selectAttach(openSession, attach_code);
		assertNotNull(selectAttach);
	}
	@Test
	public void testGetAttachCnt() {
		int cnt = dao.getAttachCnt(openSession);
		assertNotNull(cnt);
	}
	@Test
	public void testGetAttachMax() {
		int AttachCnt = dao.getAttachMax(openSession);
		assertEquals(2,	AttachCnt);
	}
	@Test
	public void testInsertAttach() {
		AttachVO attachVO = new AttachVO("1", "brown.png", "d:\\picture\\", "2");
		int insertAttach = dao.insertAttach(openSession, attachVO);
		assertNotNull(insertAttach);
	}
	@Test
	public void testUpdateAttach() {
		AttachVO attachVO = new AttachVO("1", "brownUpd.png", "d:\\picture\\", "2");
		int updateAttach = dao.updateAttach(openSession, attachVO);
		assertNotNull(updateAttach);
	}

}
