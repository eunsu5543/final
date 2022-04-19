package project.repository;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberDao {
	Logger logger = LogManager.getLogger(MemberDao.class);

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate = null;

	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("memberList DAO 호출 성공");
		List<Map<String, Object>> memberList = null;
		memberList = sqlSessionTemplate.selectList("memberList", pMap);
		logger.info("boardList : "+memberList);
		return memberList;
	}


	public Map<String, Object> loginAction(Map<String, Object> pMap) {
		logger.info("memberList DAO 호출 성공"+pMap);
		 sqlSessionTemplate.selectOne("proc_login2022",pMap);
		logger.info("rmap : " +pMap);
		return pMap;
	}


	public int memberUserInsert(Map<String, Object> pMap) {
		int result = 0;
		result = sqlSessionTemplate.insert("memberUserInsert",pMap);
		logger.info("result ====>" +result);
		return result;
	}

	public int memberBizInsert(Map<String, Object> pMap) {
		int result = 0;
		result = sqlSessionTemplate.insert("memberBizInsert",pMap);
		logger.info("result ====>" +result);
		return result;
	}


	public Map<String, Object> FindId(Map<String, Object> pMap) {
		Map<String, Object> FindId = null;
		FindId = sqlSessionTemplate.selectOne("findId", pMap);
		logger.info("FindId : "+FindId);
		
		return FindId;
	}


	public Map<String, Object> FindPw(Map<String, Object> pMap) {
		Map<String, Object> FindPw = null;
		FindPw = sqlSessionTemplate.selectOne("findPw", pMap);
		logger.info("FindId : "+FindPw);
		return FindPw;
	}


	public int UpdatePw(Map<String, Object> pMap) {
		int result = 0;
		result = sqlSessionTemplate.update("UpdatePw",pMap);
		logger.info("result ====>" +result);
		return result;
	}


	public List<Map<String, Object>> memberUserEmailSerch(Map<String, Object> pMap) {
		List<Map<String, Object>> memberUserEmailSerch = null;
		memberUserEmailSerch = sqlSessionTemplate.selectList("memberUserEmailSerch", pMap);
		logger.info("memberUserEmailSerch : "+memberUserEmailSerch);
		return memberUserEmailSerch;
	}


	public List<Map<String, Object>> memberUserIdSerch(Map<String, Object> pMap) {
		List<Map<String, Object>> memberUserIdSerch = null;
		memberUserIdSerch = sqlSessionTemplate.selectList("memberUserIdSerch", pMap);
		logger.info("memberUserIdSerch : "+memberUserIdSerch);
		return memberUserIdSerch;
	}


	public List<Map<String, Object>> memberUserNickSerch(Map<String, Object> pMap) {
		List<Map<String, Object>> memberUserNickSerch = null;
		memberUserNickSerch = sqlSessionTemplate.selectList("memberUserNickSerch", pMap);
		logger.info("memberUserIdSerch : "+memberUserNickSerch);
		return memberUserNickSerch;
	}

}

