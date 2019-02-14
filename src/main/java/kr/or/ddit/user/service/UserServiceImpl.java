package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao;
	
	
	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
	}

	/**
	* Method : getAllUser
	* 작성자 : PC04
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	public List<UserVO> getAllUser(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<UserVO> list =userDao.getAllUser(openSession);
		openSession.close();
		return list;
	}

	/**
	* Method : selectUser
	* 작성자 : PC04
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 틎정 사용자 조회
	*/
	@Override
	public UserVO selectUser(String userId) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		UserVO vo = userDao.selectUser(openSession,userId);
		openSession.close();
		return vo;
	}

	@Override
	public Map<String, Object> selectUserPagingList(PageVO pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("userList", userDao.selectUserPagingList(openSession,pageVo));
		resultMap.put("getUserCnt", userDao.getUserCnt(openSession));
		openSession.close();
		return resultMap;
	}

	@Override
	public int insertUser(UserVO userVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int num =userDao.insertUser(openSession,userVo);
		openSession.commit();
		openSession.close();
		return num;
	}

	@Override
	public int deleteUser(String userId) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int num =userDao.deleteUser(openSession,userId);
		openSession.close();
		return num;
	}

	@Override
	public int updateUser(UserVO userVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int num =userDao.updateUser(openSession,userVo);
		openSession.commit();
		openSession.close();
		return num;
	}

	@Override
	public void encryptPass(String userId) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		UserVO selectUser = userDao.selectUser(openSession,userId);
		String plainPass = selectUser.getPass();
		String encrypt = KISA_SHA256.encrypt(plainPass);
		selectUser.setPass(encrypt);
		userDao.updateUser(openSession, selectUser);
		openSession.commit();
		openSession.close();
	};
	@Override
	public void encryptPassAll() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<UserVO> allUser = userDao.getAllUser(openSession);
		for (UserVO vo :allUser) {
			String plainPass = vo.getPass();
			String encrypt = KISA_SHA256.encrypt(plainPass);
			vo.setPass(encrypt);
			userDao.updateUserPass(openSession, vo);
		}
		openSession.commit();
		openSession.close();
	};
}
