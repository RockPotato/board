package kr.or.ddit.board_class.service;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board_class.model.Board_classVO;

import org.junit.Before;
import org.junit.Test;

public class Board_classServiceImpltest {
	private IBoard_classService service;
	
	@Before
	public void setUp(){
		service = new Board_classServiceImpl();
	}


	@Test
	public void testGetAllBc() {
		List<Board_classVO> allBc = service.getAllBc();
		assertNotNull(allBc);
	}
	@Test
	public void testSelectBc() {
		String board_code = "1";
		Board_classVO selectBc = service.selectBc(board_code);
		assertNotNull(selectBc);
	}
	@Test
	public void testGetBcCnt() {
		int bcCnt = service.getBcCnt();
		assertNotNull(bcCnt);
	}
	@Test
	public void testGetBcMax() {
		int bcCnt = service.getBcMax();
		assertEquals(2, bcCnt);
	}
	@Test
	public void testInsertBc() {
		Board_classVO board_classVo=new Board_classVO("3","qna","y");
		int insertBc = service.insertBc(board_classVo);
		assertNotNull(insertBc);
	}
	@Test
	public void testUpdateBc() {
		Board_classVO board_classVo=new Board_classVO("3","qna","n");
		int updateBc = service.updateBc(board_classVo);
		assertNotNull(updateBc);
	}

}
