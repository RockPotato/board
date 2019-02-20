package kr.or.ddit.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyService {
	public List<ReplyVO> selectReplyByBn(String board_num);
	int insertReply(ReplyVO ReplyVO);
	int updateReply(ReplyVO ReplyVO);
	int getReplyMax();
	ReplyVO selectReply(String reply_code);
}
