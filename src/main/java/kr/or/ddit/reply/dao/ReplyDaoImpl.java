package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

import org.apache.ibatis.session.SqlSession;

public class ReplyDaoImpl implements IReplyDao {

	@Override
	public List<ReplyVO> selectReplyByBn(SqlSession openSession,
			String board_num) {
		List<ReplyVO> selectList = openSession.selectList("reply.selectReplyByBn",board_num);
		return selectList;
	}

	@Override
	public int insertReply(SqlSession openSession, ReplyVO ReplyVO) {
		int cnt = openSession.insert("reply.insertReply",ReplyVO);
		return cnt;
	}

	@Override
	public int updateReply(SqlSession openSession, ReplyVO ReplyVO) {
		int cnt = openSession.update("reply.updateReply",ReplyVO);
		return cnt;
	}

	@Override
	public int getReplyMax(SqlSession openSession) {
		int cnt = 0;
		if(openSession.selectOne("reply.getReplyMax")!=null)
			cnt = openSession.selectOne("reply.getReplyMax");
		return cnt;
	}

	@Override
	public ReplyVO selectReply(SqlSession openSession, String reply_code) {
		ReplyVO selectOne = openSession.selectOne("reply.selectReply",reply_code);
		return selectOne;
	}

}
