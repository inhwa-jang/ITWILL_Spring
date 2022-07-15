package com.itwillbs.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.dao.MemberDAOImpl;
import com.itwillbs.domain.MemberDTO;

public class MemberServiceImpl implements MemberService{
	//   root-context.xml  MemberDAOImpl 객체생성
	//   MemberServiceImpl에 부모인터페이스(MemberDAO) 변수(멤버변수)에 set,생성자 통해서 전달 
	
	// 부모 인터페이스변수(멤버변수 은닉)
//	MemberDAO memberDAO=new memberDAOImpl();
	private MemberDAO memberDAO;
	
	// 1. xml에서 set메서드 통해서 멤버변수에 값을 전달 
	@Inject
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	// 부모 인터페이스 (부모 틀) 상속 받아서 (부모 메서드틀) 메서드 오버라이딩(재정의) 

	@Override
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberServiceImpl insertMember()");
		
//		//1.  MemberDAOImpl 객체생성
//		//    insertMember(memberDTO) 메서드호출()
//		MemberDAOImpl memberDAOImpl=new MemberDAOImpl();
//		memberDAOImpl.insertMember(memberDTO);
		
		//2. 공통된 틀을 준비해서 => 틀에 맞추어서 각 클래스 파일 만들기
		//   부모 공통된 틀(메서드틀,추상메서드) :인터페이스 => 상속 받은 클래스 (부모 틀에 맞추어서 만듬) 
		//   패키지 com.itwillbs.dao  부모인터페이스  MemberDAO 만들기
		//                          부모인터페이스를 상속받은 MemberDAOImpl 만들기
		//   부모=자식 객체생성  MemberDAO   =  MemberDAOImpl 
		//   insertMember(memberDTO) 메서드호출()
//		MemberDAO memberDAO=new MemberDAOImpl();
//		memberDAO.insertMember(memberDTO);
		
		//3. 스프링에서 객체생성 방식
		//   스프링 파일 root-context.xml 에서 객체생성해서 
		//   객체생성을 필요로 하는 파일에 전달 
		//   root-context.xml  MemberDAOImpl 객체생성
		//   MemberServiceImpl에   부모인터페이스(MemberDAO) 변수(멤버변수)에 set,생성자 통해서 전달
		//   insertMember(memberDTO) 메서드 호출
		
		//  현 시스템 날짜 저장
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		memberDAO.insertMember(memberDTO);
		
	}

	@Override
	public MemberDTO userCheck(MemberDTO memberDTO) {
		return memberDAO.userCheck(memberDTO);
	}


	
	
}
