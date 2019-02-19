package kr.or.ddit.attach.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attach.model.AttachVO;

public interface IAttachService {
	List<AttachVO> getAllAttach();
	public AttachVO selectAttach(String attach_code);
	int getAttachCnt();
	int insertAttach(AttachVO attachVo);
	int updateAttach(AttachVO attachVo);
	int getAttachMax();
	List<AttachVO> selectAttachByBn(String board_num);
}
