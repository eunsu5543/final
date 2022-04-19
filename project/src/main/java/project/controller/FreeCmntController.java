package project.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import project.service.FreeCmntLogic;

@RestController
@RequestMapping("/freecmnt")
public class FreeCmntController {

	Logger logger = LogManager.getLogger(FreeCmntController.class);
	
	@Autowired
	private FreeCmntLogic freeCmntLogic;
	
	//자유게시판 댓글 목록
	@GetMapping("")
	public String getFreeCmntList(@RequestParam int free_num) {
		logger.info("RestFreeCmntController jsonFreeCmntList 호출 성공");
		List<Map<String,Object>> freeCmntList = null;
		freeCmntList = freeCmntLogic.getFreeCmntList(free_num);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(freeCmntList);
		return result;
	}
	
	//자유게시판 댓글 쓰기
	@PostMapping("/write")
	public String insertFreeCmnt(@RequestBody Map<String,Object> pMap) {
		logger.info("RestFreeCmntController insertFreeCmnt 호출 성공");
		logger.info("RestFreeCmntController insertFreeCmnt pMap : "+pMap);
		pMap.put("mem_num", pMap.remove("writer"));
		pMap.put("free_cmnt_content", pMap.remove("commentValue"));
		pMap.put("free_cmnt_regdate", pMap.remove("dateValue"));
		freeCmntLogic.insertFreeCmnt(pMap);
		return "입력완료";
	}
	
	//자유게시판 댓글 삭제
	@DeleteMapping("/delete")
	public String deleteFreeCmnt(@RequestParam int free_cmnt_num) {
		logger.info("RestFreeCmntController deleteFreeCmnt 호출 성공");
		freeCmntLogic.deleteFreeCmnt(free_cmnt_num);
		return "삭제완료";			
	}
	
}
