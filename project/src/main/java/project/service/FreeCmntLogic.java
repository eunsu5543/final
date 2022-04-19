package project.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.repository.FreeCmntDao;

@Service
public class FreeCmntLogic {

	Logger logger = LogManager.getLogger(FreeCmntLogic.class);
	
	@Autowired
	private FreeCmntDao freeCmntDao = null;
	
	public List<Map<String, Object>> getFreeCmntList(int free_num) {
		logger.info("FreeCmntLogic getFreeCmntList 호출 성공");
		List<Map<String,Object>> freeCmntList = null;
		freeCmntList = freeCmntDao.getFreeCmntList(free_num);
		return freeCmntList;
	}
	
	public int insertFreeCmnt(Map<String,Object> pMap) {
		logger.info("FreeBoardLogic insertFreeCmnt 호출 성공");
		logger.info("FreeBoardLogic insertFreeCmnt pMap : "+pMap);
		int result = 0;
		result = freeCmntDao.insertFreeCmnt(pMap);
		return result;
	}

	public int deleteFreeCmnt(int free_cmnt_num) {
		logger.info("FreeBoardLogic deleteFreeCmnt 호출 성공");
		int result = 0;
		result = freeCmntDao.deleteFreeCmnt(free_cmnt_num);
		return result;
	}
	
}
