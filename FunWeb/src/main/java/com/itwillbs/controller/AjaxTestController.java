package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

@RestController
public class AjaxTestController {
	//객체생성
		@Inject
		private MemberService memberService;
		
	// http://localhost:8080/FunWeb/test/iddupcheck?id=lee
	@RequestMapping(value = "/test/iddupcheck", method = RequestMethod.GET)
	public ResponseEntity<String> dupcheck(HttpServletRequest request) {
		ResponseEntity<String> entity=null;
		String result="";
				
		String id=request.getParameter("id");
		MemberDTO memberDTO=memberService.getMember(id);
		if(memberDTO!=null) {
			//아이디 있음 => 아이디 중복
			result="iddup";
		}else {
			//아이디 없음 => 아이디 사용가능
			result="idok";
		}
		entity=new ResponseEntity<String>(result,HttpStatus.OK);
		return entity;
	}
	
	// http://localhost:8080/FunWeb/test/emaildupcheck?email=lee@n.com
	@RequestMapping(value = "/test/emaildupcheck", method = RequestMethod.GET)
	public ResponseEntity<String> emaildupcheck(HttpServletRequest request) {
		ResponseEntity<String> entity=null;
		String result="";
				
		String email=request.getParameter("email");
		MemberDTO memberDTO=memberService.getMemberEmail(email);
		if(memberDTO!=null) {
			//아이디 있음 => 아이디 중복
			result="emaildup";
		}else {
			//아이디 없음 => 아이디 사용가능
			result="emailok";
		}
		entity=new ResponseEntity<String>(result,HttpStatus.OK);
		return entity;
	}
	
	//	http://localhost:8080/FunWeb/test/jsonlist
	@RequestMapping(value = "/test/jsonlist", method = RequestMethod.GET)
	public ResponseEntity<List<MemberDTO>> jsonlist(HttpServletRequest request) {
		ResponseEntity<List<MemberDTO>> entity=null;
				
		List<MemberDTO> memberList=memberService.getMemberList();
		
		// List<MemberDTO> => json 형으로 변경하는 프로그램 설치 (jackson-databind) 

		entity=new ResponseEntity<List<MemberDTO>>(memberList,HttpStatus.OK);
		return entity;
	}
}
