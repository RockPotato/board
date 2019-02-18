package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

import org.apache.ibatis.session.SqlSession;

public interface IReplyDao {
	public List<ReplyVO> selectReplyByBn(SqlSession openSession,String board_num);
	int insertReply(SqlSession openSession,ReplyVO ReplyVO);
	int updateReply(SqlSession openSession,ReplyVO ReplyVO);
	int getReplyMax(SqlSession openSession);
}
