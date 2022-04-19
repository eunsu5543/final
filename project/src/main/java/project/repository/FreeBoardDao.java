package project.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FreeBoardDao {

	Logger logger = LogManager.getLogger(FreeBoardDao.class);
	
	private static final String NAMESPACE = "project.repository.";
	
	@Autowired
	private SqlSession sqlSession = null;
		
	public List<Map<String,Object>> getFreeBoardList(Map<String,Object> pMap) {
		logger.info("FreeBoardDao getFreeBoardList 호출 성공");
			List<Map<String,Object>> freeBoardList = sqlSession.selectList(NAMESPACE+"getFreeBoardList",pMap);
			logger.info("freeBoardList : " + freeBoardList);		
		return freeBoardList;
	}
	
	public List<Map<String, Object>> searchFreeBoard(String keyword) {
		logger.info("FreeBoardDao searchFreeBoard 호출 성공");
		logger.info("FreeBoardDao searchFreeBoard keyword : "+keyword);
		List<Map<String,Object>> list = null;
		try {
			list = sqlSession.selectList(NAMESPACE+"searchFreeBoard",keyword);		
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		}
		return list;		
	}
	
	public void updateFreeBoardViews(int free_num) {
		logger.info("FreeBoardDao updateFreeBoardViews 호출 성공");
		sqlSession.update(NAMESPACE+"updateFreeBoardViews", free_num);
	}
	
	public int insertFreeBoard(Map<String, Object> pMap) {
		logger.info("FreeBoardDao insertFreeBoard 호출 성공");
		logger.info("FreeBoardDao insertFreeBoard pMap : "+pMap);

		int result = 0;
		result = sqlSession.insert(NAMESPACE+"insertFreeBoard",pMap);
		logger.info("FreeBoardDao insertFreeBoard result : "+result);
		return result;
	}
	
	public int getFreeNum() { //다시 체크
		int result = 0;
		try { result = sqlSession.selectOne("getFreeNum"); } 
		catch (Exception e) { logger.info("Exception : "+e.toString()); }
		return result;
	}
		
	public int updateFreeBoard(Map<String, Object> pMap) {
		logger.info("FreeBoardDao updateFreeBoard 호출 성공");
		logger.info("FreeBoardDao updateFreeBoard free_num : "+pMap);
		int result = 0;
		result = sqlSession.update(NAMESPACE+"updateFreeBoard",pMap);
		return result;
	}

	public int deleteFreeBoard(int free_num) {
		logger.info("FreeBoardDao deleteFreeBoard 호출 성공");
		int result = 0;
		result = sqlSession.delete(NAMESPACE+"deleteFreeBoard",free_num);
		return result;
	}
		
}	
	

