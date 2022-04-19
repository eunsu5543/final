package project.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.repository.MemberDao;

@Service
public class MemberLogic {
	Logger logger = LogManager.getLogger(MemberLogic.class);
	
	@Autowired
	private MemberDao memberdao = null;
	
	
	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		List<Map<String, Object>> memberList = null;
		memberList=memberdao.memberList(pMap);
		
		return memberList;
	}

	public Map<String, Object> loginAction(Map<String, Object> pMap) {
		Map<String,Object> rmap=memberdao.loginAction(pMap);
		
		return rmap;
	}
	
	public Map<String, Object> FindId(Map<String, Object> pMap) {
		Map<String,Object> rmap=memberdao.FindId(pMap);
		return rmap;
	}
	
	public Map<String, Object> findPw(Map<String, Object> pMap) {
		Map<String,Object> rmap=memberdao.FindPw(pMap);
		return rmap;
	}
	


	public int memberUserInsert(Map<String, Object> pMap) {
		int result = 0;
		result = memberdao.memberUserInsert(pMap);
			return result;
	}

	public int memberBizInsert(Map<String, Object> pMap) {
		int result = 0;
		result = memberdao.memberBizInsert(pMap);
			return result;
	}

	public int UpdatePw(Map<String, Object> pMap) {
		int result = 0;
			result=memberdao.UpdatePw(pMap);
		return result;
	}

	public List<Map<String, Object>> memberUserEmailSerch(Map<String, Object> pMap) {
		List<Map<String,Object>> rmap=memberdao.memberUserEmailSerch(pMap);
		return rmap;
	}

	public List<Map<String, Object>> memberUserIdSerch(Map<String, Object> pMap) {
		List<Map<String,Object>> rmap=memberdao.memberUserIdSerch(pMap);
		return rmap;
	}

	public List<Map<String, Object>> memberUserNickSerch(Map<String, Object> pMap) {
		List<Map<String,Object>> rmap=memberdao.memberUserNickSerch(pMap);
		return rmap;
	}

}

