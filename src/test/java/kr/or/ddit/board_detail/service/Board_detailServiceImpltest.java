package kr.or.ddit.board_detail.service;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board_detail.model.Board_detailVO;

import org.junit.Before;
import org.junit.Test;

public class Board_detailServiceImpltest {
	private IBoard_detailService service;

	@Before
	public void setUp() {
		service = new Board_detailServiceImpl();
	}

	@Test
	public void testGetAllBd() {
		List<Board_detailVO> allBd = service.getAllBd();
		assertNotNull(allBd);
	}

	@Test
	public void testSelectBd() {
		String board_code = "1";
		Board_detailVO selectBd = service.selectBd(board_code);
		assertNotNull(selectBd);
	}

	@Test
	public void testGetBdCnt() {
		String board_code = "1";
		int BdCnt = service.getBdCnt(board_code);
		assertNotNull(BdCnt);
	}

	@Test
	public void testGetBdMax() {
		int BdCnt = service.getBdMax();
		assertEquals(2, BdCnt);
	}

	@Test
	public void testInsertBd() {
		Board_detailVO Board_detailVO = new Board_detailVO("3", "lee", "haha",
				"1", "lee", "");
		int insertBd = service.insertBd(Board_detailVO);
		assertNotNull(insertBd);
	}

	@Test
	public void testUpdateBd() {
		Board_detailVO Board_detailVO = new Board_detailVO("3", "lee", "haha",
				"1", "lee", "");
		Board_detailVO.setDel_check("y");
		int updateBd = service.updateBd(Board_detailVO);
		assertNotNull(updateBd);
	}

}
