package kr.or.ddit.attach.dao;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;

public interface IAttachDao {
	List<AttachVO> getAllAttach(SqlSession openSession);
	public AttachVO selectAttach(SqlSession openSession,String attach_code);
	public List<AttachVO> selectAttachByBn(SqlSession openSession,String board_num);
	int getAttachCnt(SqlSession openSession);
	int insertAttach(SqlSession openSession,AttachVO AttachVO);
	int updateAttach(SqlSession openSession,AttachVO AttachVO);
	int getAttachMax(SqlSession openSession);
}
