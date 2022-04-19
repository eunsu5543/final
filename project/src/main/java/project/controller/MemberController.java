package project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import project.service.MemberLogic;

	@RestController
	@RequestMapping("/*")
	public class MemberController {
	Logger logger = LogManager.getLogger(MemberController.class);

		@Autowired
		private MemberLogic memberLogic = null;
		
		
		@GetMapping("memberLogin")
		public Map<String,Object> memberLogin(@RequestParam Map<String,Object> pMap, HttpSession session ) {
			logger.info("MemberLogin 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			String S_memid=null;
			Gson g = new Gson();
			Map<String,Object> memberList = null;
			memberList = memberLogic.loginAction(pMap);
			logger.info("memberlist===========>"+memberList);
//				S_memid=g.toJson(memberList.get("mem_nick"));
			session.setAttribute("mem_id", memberList.get("mem_id").toString());
			session.setAttribute("mem_num", memberList.get("mem_num").toString());
			
			return memberList;
				
				
		}
		
		@GetMapping("memberLogOut")
		public  @ResponseBody String memberLogout(HttpServletRequest request, Model model, HttpSession session) {
				//setComplete 메소드 호출로 해당세션 폐기
			String mem_id = (String)session.getAttribute("mem_id");
			String mem_nick = (String)session.getAttribute("mem_nick");
			String msg=null;
			session.invalidate();
				if(request.isRequestedSessionIdValid() == true) {
					logger.info("sessionStatus 호출성공");
					msg="세션아이디: "+ mem_id + ", 세션 닉네임: "+mem_nick;
					return msg;//  /spring 으로 리다이렉트
				}
				else {
					msg="로그아웃되었습니다!";
					String loc="/";
					return msg;//  /spring 으로 리다이렉트
				}
		}
		
		@GetMapping("findId")
		public  @ResponseBody String FindId(@RequestParam Map<String,Object> pMap) {
			Map<String,Object> findId = null;
			findId=memberLogic.FindId(pMap);
			String S_memid=null;
			Gson g = new Gson();
			S_memid=g.toJson(findId.get("MEM_ID").toString());
			return S_memid;//  /spring 으로 리다이렉트
		}
		
		@GetMapping("findPw")
		public Map<String,Object> FindPw(@RequestParam Map<String,Object> pMap) {
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			Map<String,Object> findPw = null;
			findPw=memberLogic.findPw(pMap);
//			String S_memid=null;
//			Gson g = new Gson();
//			S_memid=g.toJson(findPw.toString());
			return findPw;
		}
		
		@GetMapping("UpdatePw")
		public  @ResponseBody String UpdatePw(@RequestParam Map<String,Object> pMap) {
			int result=0;
			result=memberLogic.UpdatePw(pMap);
			return "비밀번호가 변경되었습니다.";
		}
		
		@GetMapping("userInfo")
		public @ResponseBody String userInfo(@RequestParam Map<String,Object>pMap, HttpSession session) {
			String mem_id = (String)session.getAttribute("mem_id");
			String mem_nick = (String)session.getAttribute("mem_nick");
			return "세션이름: "+ mem_id + ", 세션 아이디: "+mem_nick;
		}
	
		/////////////////////개인회원 아이디 중복체크
		@GetMapping("email")
		public List<Map<String,Object>> memberUserEmailSerch(@RequestParam Map<String,Object> pMap) {
			logger.info("memberUserEmailSerch 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			List<Map<String, Object>> memberUserEmailSerch = null;
			memberUserEmailSerch = memberLogic.memberUserEmailSerch(pMap);
			logger.info(memberUserEmailSerch.get(0).get("MEM_EMAIL").toString());
			return memberUserEmailSerch;
		}
		@GetMapping("id")
		public List<Map<String,Object>> memberUserIdSerch(@RequestParam Map<String,Object> pMap) {
			logger.info("memberUserIdSerch 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			List<Map<String, Object>> memberUserIdSerch = null;
			memberUserIdSerch = memberLogic.memberUserIdSerch(pMap);
			return memberUserIdSerch;
		}
		@GetMapping("nick")
		public List<Map<String,Object>> memberUserNickSerch(@RequestParam Map<String,Object> pMap) {
			logger.info("memberUserNickSerch 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			List<Map<String, Object>> memberUserNickSerch = null;
			memberUserNickSerch = memberLogic.memberUserNickSerch(pMap);
			logger.info(memberUserNickSerch.get(0).get("MEM_NICK").toString());
			return memberUserNickSerch;
		}
		
//////////////////////		////////////////////////////////개인회원중복 확인
		
		@PostMapping("regist")
		public String memberUserInsert(@RequestParam Map<String,Object> pMap) {
			logger.info("MemberUserInsert 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			int result = 0;
			result = memberLogic.memberUserInsert(pMap);
			
			return "회원가입에성공했습니다.";
		}
		
		@GetMapping("memberBizInsert")
		public String memberBizInsert(@RequestParam Map<String,Object> pMap) {
			logger.info("MemberInsert 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			int result = 0;
			result = memberLogic.memberBizInsert(pMap);
			return "회원가입에성공했습니다.";
		}
		@GetMapping("memberUserUpdate")
		public String memberUserUpdate(@RequestParam Map<String,Object> pMap) {
			logger.info("MemberUserInsert 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			int result = 0;
			result = memberLogic.memberUserInsert(pMap);
			
			return "회원가입에성공했습니다.";
		}
		
		@GetMapping("memberBizUpdate")
		public String memberBizUpdate(@RequestParam Map<String,Object> pMap) {
			logger.info("MemberInsert 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			int result = 0;
			result = memberLogic.memberBizInsert(pMap);
			return "회원가입에성공했습니다.";
		}
		
		@GetMapping("memberUserDelete")
		public String memberUserDelete(@RequestParam Map<String,Object> pMap) {
			logger.info("MemberUserInsert 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			int result = 0;
			result = memberLogic.memberUserInsert(pMap);
			
			return "회원가입에성공했습니다.";
		}
		
		@GetMapping("memberBizDelete")
		public String memberBizDelete(@RequestParam Map<String,Object> pMap) {
			logger.info("MemberInsert 호출 성공");
			logger.info("사용자가 입력한 정보 ==> "+pMap);
			int result = 0;
			result = memberLogic.memberBizInsert(pMap);
			return "회원가입에성공했습니다.";
		}
		
		
		
		
		
}

