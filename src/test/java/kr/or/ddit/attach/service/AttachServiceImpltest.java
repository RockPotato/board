package kr.or.ddit.attach.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;

import org.junit.Before;
import org.junit.Test;

public class AttachServiceImpltest {
	private IAttachService service;
	
	@Before
	public void setUp(){
		service = new AttachServiceImpl();
	}


	@Test
	public void testGetAllAttach() {
		List<AttachVO> allAttach = service.getAllAttach();
		assertNotNull(allAttach);
	}
	@Test
	public void testSelectAttach() {
		AttachVO vo = new AttachVO();
		vo.setAttach_code("1");
		vo.setBoard_num("101");
		AttachVO selectAttach = service.selectAttach(vo);
		assertNotNull(selectAttach);
	}
	@Test
	public void testGetAttachCnt() {
		int AttachCnt = service.getAttachCnt();
		assertNotNull(AttachCnt);
	}
	@Test
	public void testGetAttachMax() {
		String board_num ="12";
		int AttachCnt = service.getAttachMax(board_num);
		assertEquals(2, AttachCnt);
	}
	@Test
	public void testInsertAttach() {
		AttachVO AttachVO=new AttachVO("2", "cony.png", "d:\\picture\\", "12","cony.png");
		int insertAttach = service.insertAttach(AttachVO);
		assertNotNull(insertAttach);
	}
	@Test
	public void testUpdateAttach() {
		AttachVO AttachVO=new AttachVO("2", "cony.png", "d:\\picture\\", "12","cony.png");
		int updateAttach = service.updateAttach(AttachVO);
		assertNotNull(updateAttach);
	}

}
