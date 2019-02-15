package kr.or.ddit.board_class.dao;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.board_class.dao.Board_classDaoImpl;
import kr.or.ddit.board_class.dao.IBoard_classDao;
import kr.or.ddit.board_class.model.Board_classVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Board_classDaoImpltest {
	private SqlSession openSession;
	private IBoard_classDao bcDao;

	@Before
	public void setup() {
		bcDao = new Board_classDaoImpl();
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
	public void testGetAllBc() {
		List<Board_classVO> allBc = bcDao.getAllBc(openSession);
		assertNotNull(allBc);
	}
	@Test
	public void testSelectBc() {
		String board_code = "1";
		Board_classVO selectBc = bcDao.selectBc(openSession, board_code);
		assertNotNull(selectBc);
	}
	@Test
	public void testGetBcCnt() {
		int bcCnt = bcDao.getBcCnt(openSession);
		assertNotNull(bcCnt);
	}
	@Test
	public void testGetBcMax() {
		int bcCnt = bcDao.getBcMax(openSession);
		assertEquals(2,	bcCnt);
	}
	@Test
	public void testInsertBc() {
		
		Board_classVO board_classVo=new Board_classVO("2","자유게시판","y");
		int insertBc = bcDao.insertBc(openSession, board_classVo);
		assertNotNull(insertBc);
	}
	@Test
	public void testUpdateBc() {
		Board_classVO board_classVo=new Board_classVO("2","자유게시판","n");
		int updateBc = bcDao.updateBc(openSession, board_classVo);
		assertNotNull(updateBc);
	}

}
