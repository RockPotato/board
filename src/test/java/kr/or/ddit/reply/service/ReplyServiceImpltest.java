package kr.or.ddit.reply.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

import org.junit.Before;
import org.junit.Test;

public class ReplyServiceImpltest {
	private IReplyService service;
	
	@Before
	public void setUp(){
		service = new ReplyServiceImpl();
	}


	@Test
	public void testSelectReplyByBn() {
		String board_num = "200";
		List<ReplyVO> selectReplyByBn = service.selectReplyByBn(board_num);
		assertEquals(3,selectReplyByBn.size());
	}
	@Test
	public void testGetReplyMax() {
		int ReplyCnt = service.getReplyMax();
		assertEquals(12, ReplyCnt);
	}
	@Test
	public void testInsertReply() {
		ReplyVO ReplyVO = new ReplyVO("12", "love me love me", "54", "lee");
		int insertReply = service.insertReply(ReplyVO);
		assertNotNull(insertReply);
	}
	@Test
	public void testUpdateReply() {
		ReplyVO ReplyVO = new ReplyVO("12", "너와 함께 춤추고 싶어", "54", "lee");
		int updateReply = service.updateReply(ReplyVO);
		assertNotNull(updateReply);
	}

}
