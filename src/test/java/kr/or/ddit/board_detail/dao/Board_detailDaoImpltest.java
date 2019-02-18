package kr.or.ddit.board_detail.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.board_detail.model.Board_detailVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Board_detailDaoImpltest {
	private SqlSession openSession;
	private IBoard_detailDao dao;

	@Before
	public void setup() {
		dao = new Board_detailDaoImpl();
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
	public void testGetAllBd() {
		List<Board_detailVO> allBd = dao.getAllBd(openSession);
		assertNotNull(allBd);
	}
	@Test
	public void testSelectBd() {
		String board_code = "1";
		Board_detailVO selectBd = dao.selectBd(openSession, board_code);
		assertNotNull(selectBd);
	}
	@Test
	public void testGetBdCnt() {
		String board_code = "1";
		int BdCnt = dao.getBdCnt(openSession,board_code);
		assertNotNull(BdCnt);
	}
	@Test
	public void testGetBdMax() {
		int BdCnt = dao.getBdMax(openSession);
		assertEquals(1,	BdCnt);
	}
	@Test
	public void testInsertBd() {
		Board_detailVO Board_detailVO=new Board_detailVO("2","lee","haha","1","lee","");
		int insertBd = dao.insertBd(openSession, Board_detailVO);
		assertNotNull(insertBd);
	}
	@Test
	public void testUpdateBd() {
		Board_detailVO Board_detailVO=new Board_detailVO("2","lee","haha","1","brown","");
		Board_detailVO.setDel_check("y");
		int updateBd = dao.updateBd(openSession, Board_detailVO);
		assertNotNull(updateBd);
	}

}
