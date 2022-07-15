package com.itwillbs.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;



@Controller
public class MemberController {
	//객체생성
	@Inject
	private MemberService memberService;

	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		
		// /WEB-INF/views/member/join.jsp
		return "member/join";
	}
	
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberDTO memberDTO) {
		//디비 insertMember() 메서드호출
		
		memberService.insertMember(memberDTO);
		
		return "redirect:/member/login";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		
		// /WEB-INF/views/member/login.jsp
		return "member/login";
	}
	
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO,HttpSession session) {
		
		MemberDTO memberDTO2=memberService.userCheck(memberDTO);
		
		if(memberDTO2!=null) {
			//아이디 비밀번호 일치 
			//세션값 생성 "id",id
			session.setAttribute("id", memberDTO.getId());
			return "redirect:/main/main";
		}else {
			//아이디 비밀번호 틀림 뒤로이동
			return "member/msg"; 
		}
	}
	
	@RequestMapping(value = "/main/main", method = RequestMethod.GET)
	public String main() {
		
		// /WEB-INF/views/main/main.jsp
		return "main/main";
	}
	
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/main/main";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session,Model model) {
		String id=(String)session.getAttribute("id");
		
		MemberDTO memberDTO=memberService.getMember(id);
		
		model.addAttribute("memberDTO", memberDTO);
		
		// /WEB-INF/views/member/update.jsp
		return "member/update";
	}
	
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro(MemberDTO memberDTO) {
		
		MemberDTO memberDTO2=memberService.userCheck(memberDTO);
		
		if(memberDTO2!=null) {
			//아이디 비밀번호 일치 
			//수정
			memberService.updateMember(memberDTO);
			return "redirect:/main/main";
		}else {
			//아이디 비밀번호 틀림 뒤로이동
			return "member/msg"; 
		}
	}
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list() {
		
		// /WEB-INF/views/member/list.jsp
		return "member/list";
	}
	
	//http://localhost:8080/FunWeb/test/test1
	@RequestMapping(value = "/test/test1", method = RequestMethod.GET)
	public String test1() {
		
		// /WEB-INF/views/test/test1.jsp
		return "test/test1";
	}
	
//	public static List<NameValuePair> convertParameter(Map<String,String> paramMap){
//        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//        Set<Map.Entry<String,String>> entries = paramMap.entrySet();
//        for(Map.Entry<String,String> entry : entries) {
//            paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        } return paramList;
//    }
	
	//http://localhost:8080/FunWeb/test/test2
	@RequestMapping(value = "/test/test2", method = RequestMethod.GET)
	public String test2() {
		
//		 String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
//		  String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";
//		  String KEY = "3277120266459676";
//		  String SECRET = "1DrQxlMwGqNfi2Efjf53cfPy17T4SIyUm0KTlxp2YdhhgWwzcBDpRsXTzRXszwnVw8PSFeUEscVI9ril";
//		    
//		    String result = "";
//	        HttpClient client = HttpClientBuilder.create().build();
//	        HttpPost post = new HttpPost(IMPORT_TOKEN_URL);
//	        Map<String,String> m =new HashMap<String,String>();
//	        m.put("imp_key", KEY);
//	        m.put("imp_secret", SECRET);
//	        try {
//	            post.setEntity(new UrlEncodedFormEntity(convertParameter(m)));
//	            HttpResponse res = client.execute(post);
//	            ObjectMapper mapper = new ObjectMapper();
//	            String body = EntityUtils.toString(res.getEntity());
//	            JsonNode rootNode = mapper.readTree(body);
//	            JsonNode resNode = rootNode.get("response");
//	            result = resNode.get("access_token").asText();
//	            System.out.println(result);
//	        } catch (Exception e){
////	            throw new IamportException("아임포트 토큰을 받아올 수 없습니다.");
//	           e.printStackTrace();
//	        }
	        
		
		// /WEB-INF/views/test/test2.jsp
		return "test/test2";
	}
	
	
	
}
