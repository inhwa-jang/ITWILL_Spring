package com.itwillbs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.MemberServiceImpl;

@Controller
public class MemberController {
	
	//    MemberController에서 MemberServiceImpl파일 필요로 해서 객체생성 메서드호출
	//	  MemberController에서 MemberServiceImpl파일 의존해서 객체생성 메서드호출
	//    MemberController는 MemberServiceImpl에 의존하는 관계(의존관계)
	//    xml MemberServiceImpl객체생성 MemberController 전달(의존관계 주입:DI(Dependency Injection))
	
	
	//   root-context.xml  MemberServiceImpl 객체생성
	//   MemberController에 부모인터페이스(MemberService) 변수(멤버변수)에 set,생성자 통해서 전달 
	
	// 부모 인터페이스변수(멤버변수 은닉)
//	MemberService memberService=new MemberServiceImpl();
	private MemberService memberService;
	
	// 1. xml에서 set메서드 통해서 멤버변수에 값을 전달 
	@Inject
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	// 2. xml에서 생성자를 통해서 멤버변수에 값을 전달
//	@Inject
//	public MemberController(MemberService memberService) {
//		//생성자 
//		//멤버변수 = xml에서 전달받은 매개변수
//		this.memberService=memberService;
//	}
	
	
//	http://localhost:8080/myweb/member/insert
	


	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		System.out.println("MemberController insert()");
//		/WEB-INF/views/member/insertForm.jsp
		// 주소 안바뀌면서 이동
//		RequestDispatcher dispatcher=request.getRequestDispatcher("home.jsp");
//		dispatcher.forward(request, response);
		return "member/insertForm";
	}
	
	// http://localhost:8080/myweb/member/insertPro
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberDTO memberDTO) {
		System.out.println("MemberController insertPro()");
		// 폼에 파라미터 이름 , MemberDTO 멤버변수 이름 일치하면 자동으로 값이 저장
//		// 파라미터 가져오기
		// HttpServletRequest request 매개변수 받기
//		String id=request.getParameter("id");
//		String pass=request.getParameter("pass");
//		String name=request.getParameter("name");
//		// MemberDTO 객체생성
//		MemberDTO memberDTO=new MemberDTO();
//		// set메서드 호출 파라미터값 저장
//		memberDTO.setId(id);
//		memberDTO.setPass(pass);
//		memberDTO.setName(name);
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());
		
		// 디비에 insert() 
		// 화면 jsp
		// 주소매칭 MemberController 패키지 com.itwillbs.controller
		// 처리작업 MemberService   패키지 com.itwillbs.service  파일 MemberServiceImpl
		// 디비작업 MemberDAO       패키지 com.itwillbs.dao      파일 MemberDAOImpl
		
		//1.  MemberServiceImpl 객체생성
		//    insertMember(memberDTO) 메서드호출()
		//    MemberController에서 MemberServiceImpl파일 필요로 해서 객체생성 메서드호출
		//	  MemberController에서 MemberServiceImpl파일 의존해서 객체생성 메서드호출
		//    MemberController는 MemberServiceImpl에 의존하는 관계(의존관계)
//		MemberServiceImpl memberServiceImpl=new MemberServiceImpl();
//		memberServiceImpl.insertMember(memberDTO);
		
		//2. 공통된 틀을 준비해서 => 틀에 맞추어서 각 클래스 파일 만들기
		//   부모 공통된 틀(메서드틀,추상메서드) :인터페이스 => 상속 받은 클래스 (부모 틀에 맞추어서 만듬) 
		//   패키지 com.itwillbs.service  부모인터페이스  MemberService 만들기
		//                              부모인터페이스를 상속받은 MemberServiceImpl 만들기
		//   부모=자식 객체생성  MemberService   =  MemberServiceImpl 
		//   insertMember(memberDTO) 메서드호출()
//		MemberService memberService=new MemberServiceImpl();
//		memberService.insertMember(memberDTO);
		
		//3. 스프링에서 객체생성 방식
		//   스프링 파일 root-context.xml 에서 객체생성해서 
		//   객체생성을 필요로 하는 파일에 전달 
		//   root-context.xml  MemberServiceImpl 객체생성
		//   MemberController에 부모인터페이스(MemberService) 변수(멤버변수)에 set,생성자 통해서 전달
		//   insertMember(memberDTO) 메서드 호출
		memberService.insertMember(memberDTO);
		
		// 주소 변경하면서 이동
//		response.sendRedirect()
		return "redirect:/member/login";
	}
	
	//http://localhost:8080/myweb/member/login  =>  member/loginForm.jsp  GET방식
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login()");
//		/WEB-INF/views/member/loginForm.jsp
		// 주소 안바뀌면서 이동
//		RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
//		dispatcher.forward(request, response);
		return "member/loginForm";
	}
	
	//  /member/loginPro POST => /member/main 가상주소 이동
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO,HttpSession session) {
		System.out.println("MemberController loginPro()");
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		//로그인 처리
		MemberDTO memberDTO2=memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			//세션값 생성
			session.setAttribute("id", memberDTO.getId());
		}
		return "redirect:/member/main";
	}
	
    //  /member/main     GET  => /member/main.jsp 이동
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		System.out.println("MemberController main()");
		return "member/main";
	}
	
//	/member/logout  GET  => 가상주소 /member/main
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout() {
		System.out.println("MemberController logout()");
		return "redirect:/member/main";
	}
//	/member/info    GET  => member/info.jsp
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info() {
		System.out.println("MemberController info()");
		return "member/info";
	}
	
//	/member/update  GET  => member/updateForm.jsp
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update() {
		System.out.println("MemberController info()");
		return "member/updateForm";
	}
//	/member/updatePro POST => 가상주소 /member/main
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro() {
		System.out.println("MemberController updatePro()");
		return "redirect:/member/main";
	}
	
	
//	/member/delete  GET  => member/deleteForm.jsp
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete() {
		System.out.println("MemberController delete()");
		return "member/deleteForm";
	}
//	/member/deletePro POST => 가상주소 /member/main
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)
	public String deletePro() {
		System.out.println("MemberController deletePro()");
		return "redirect:/member/main";
	}
			
//	/member/list    GET  => member/list.jsp
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list() {
		System.out.println("MemberController list()");
		return "member/list";
	}
	
	
}
