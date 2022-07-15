package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

@Controller
public class MemberController {

	//MemberService memberService=new MemberServiceImpl()객체생성
	@Inject
	private MemberService memberService;
	
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		// /WEB-INF/views/member/insertForm.jsp
		return "member/insertForm";
	}
	
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberDTO memberDTO) {
		//회원가입 메서드호출
		memberService.insertMember(memberDTO);
		
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		// /WEB-INF/views/member/loginForm.jsp
		return "member/loginForm";
	}
	
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO,HttpSession session) {
		//로그인 메서드호출
		MemberDTO memberDTO2=memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			//아이디 비밀번호 일치
			//세션값 생성
			session.setAttribute("id", memberDTO.getId());
			return "redirect:/member/main";
		}else {
			//아이디 비밀번호 틀림
			return "member/msg";
		}
	}
	
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		// /WEB-INF/views/member/main.jsp
		return "member/main";
	}
	
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//로그아웃(세션초기화)
		session.invalidate();
		
		return "redirect:/member/main";
	}
	
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info(HttpSession session,Model model) {
		//세션값 가져오기
		String id=(String)session.getAttribute("id");
		
		// 디비작업 
		MemberDTO memberDTO=memberService.getMember(id);
		
		// memberDTO 담아서 info.jsp 이동
//		request.setAttribute("memberDTO",memberDTO);
		model.addAttribute("memberDTO", memberDTO);
		
		// /WEB-INF/views/member/info.jsp
		return "member/info";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session,Model model) {
		//세션값 가져오기
		String id=(String)session.getAttribute("id");
		
		// 디비작업 
		MemberDTO memberDTO=memberService.getMember(id);
		
		// memberDTO 담아서 updateForm.jsp 이동
//		request.setAttribute("memberDTO",memberDTO);
		model.addAttribute("memberDTO", memberDTO);
		
		// /WEB-INF/views/member/updateForm.jsp
		return "member/updateForm";
	}
	
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro(MemberDTO memberDTO) {
		//로그인 메서드호출
		MemberDTO memberDTO2=memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			//아이디 비밀번호 일치
			//수정작업
			memberService.updateMember(memberDTO);
			return "redirect:/member/main";
		}else {
			//아이디 비밀번호 틀림
			return "member/msg";
		}
	}
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete(HttpSession session,Model model) {
		// /WEB-INF/views/member/deleteForm.jsp
		return "member/deleteForm";
	}
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)
	public String deletePro(MemberDTO memberDTO,HttpSession session) {
		//로그인 메서드호출
		MemberDTO memberDTO2=memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			//아이디 비밀번호 일치
			//삭제작업
			memberService.deleteMember(memberDTO);
			//세션값 초기화
			session.invalidate();
			return "redirect:/member/main";
		}else {
			//아이디 비밀번호 틀림
			return "member/msg";
		}
	}
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list(HttpSession session,Model model) {
				
		// 디비작업 
		List<MemberDTO> memberList=memberService.getMemberList();
		
		// memberDTO 담아서 list.jsp 이동
		model.addAttribute("memberList", memberList);
		
		// /WEB-INF/views/member/list.jsp
		return "member/list";
	}
	
}
