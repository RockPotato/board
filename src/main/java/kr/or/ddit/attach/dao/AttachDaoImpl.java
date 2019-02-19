package kr.or.ddit.attach.dao;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;

import org.apache.ibatis.session.SqlSession;

public class AttachDaoImpl implements IAttachDao {

	@Override
	public List<AttachVO> getAllAttach(SqlSession openSession) {
		List<AttachVO> selectList = openSession.selectList("attach.getAllAttach");
		return selectList;
	}

	@Override
	public AttachVO selectAttach(SqlSession openSession, AttachVO vo) {
		AttachVO selectOne = openSession.selectOne("attach.selectAttach",vo);
		return selectOne;
	}

	@Override
	public List<AttachVO> selectAttachByBn(SqlSession openSession,
			String board_num) {
		List<AttachVO> selectList = openSession.selectList("attach.selectAttachByBn",board_num);
		return selectList;
	}

	@Override
	public int getAttachCnt(SqlSession openSession) {
		int selectOne = openSession.selectOne("attach.getAttachCnt");
		return selectOne;
	}

	@Override
	public int insertAttach(SqlSession openSession, AttachVO AttachVO) {
		int insert = openSession.insert("attach.insertAttach",AttachVO);
		return insert;
	}

	@Override
	public int updateAttach(SqlSession openSession, AttachVO AttachVO) {
		int update = openSession.update("attach.updateAttach",AttachVO);
		return update;
	}

	@Override
	public int getAttachMax(SqlSession openSession,String board_num) {
		int selectOne =0;
		if(openSession.selectOne("attach.getAttachMax",board_num)!=null){
			selectOne = openSession.selectOne("attach.getAttachMax",board_num);
		}
		return selectOne;
	}

}
