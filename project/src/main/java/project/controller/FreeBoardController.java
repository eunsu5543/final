package project.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import project.repository.FreeBoardDao;
import project.service.FreeBoardLogic;

@RestController
@RequestMapping("/freeboard")
public class FreeBoardController {
	
	Logger logger = LogManager.getLogger(FreeBoardController.class);
	
	@Autowired
	FreeBoardLogic freeBoardLogic;
	
	@Autowired
	FreeBoardDao freeBoardDao;
	
	//자유게시판 목록
	@GetMapping("")
	public String getFreeBoardList(Map<String, Object> pMap) {
		logger.info("FreeBoardController getFreeBoardList 호출 성공");
		List<Map<String,Object>> freeBoardList = null;
		freeBoardList = freeBoardLogic.getFreeBoardList(pMap);		
		String result = null;
		Gson g = new Gson();
		result = g.toJson(freeBoardList);
		return result;
	}
	
	//자유게시판 검색
	@GetMapping("/search")
	public String searchFreeBoard(@RequestParam String keyword) {
		logger.info("FreeBoardController searchFreeBoard 호출 성공");
		logger.info("FreeBoardController searchFreeBoard keyword"+keyword);
		List<Map<String,Object>> list = null;
		list = freeBoardLogic.searchFreeBoard(keyword);
		String result = null;
		Gson g = new Gson();
		result = g.toJson(list);
		return result;
	}
	
	//자유게시판 조회수카운트
	@PutMapping("/views")
	public void updateFreeBoardViews(@RequestParam int free_num) {
		freeBoardLogic.updateFreeBoardViews(free_num);
	}
	
	//자유게시판 글쓰기
	@PostMapping("/write")
	public String insertFreeBoard(@RequestBody Map<String,Object> pMap) throws Exception {
		logger.info("FreeBoardController insertFreeBoard 호출 성공");	
		logger.info("FreeBoardController insertFreeBoard pMap : "+pMap);
		pMap.put("mem_num", pMap.remove("writer"));
		pMap.put("free_subject", pMap.remove("categoryValue"));
		pMap.put("free_title", pMap.remove("titleValue"));
		pMap.put("free_content", pMap.remove("contentValue"));
		pMap.put("free_regdate", pMap.remove("dateValue"));
		freeBoardLogic.insertFreeBoard(pMap);
		return "입력완료";
	}
	
	//자유게시판 글수정
	@PostMapping("/update")
	public String updateFreeBoard(@RequestBody Map<String, Object> pMap) {
		logger.info("RestFreeBoardController updateFreeBoard 호출 성공");
		logger.info("RestFreeBoardController updateFreeBoard pMap : "+pMap);
		freeBoardLogic.updateFreeBoard(pMap);
		return "수정완료";
	}
	
	//자유게시판 글삭제
	@DeleteMapping("freeboard/delete")
	public String deleteFreeBoard(@RequestParam int free_num) {
		logger.info("deleteFreeBoard 호출 성공");
		freeBoardLogic.deleteFreeBoard(free_num);
		return "삭제완료";
	}
	
}
