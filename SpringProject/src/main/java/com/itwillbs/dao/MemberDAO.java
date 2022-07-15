package com.itwillbs.dao;

import com.itwillbs.domain.MemberDTO;

public interface MemberDAO {
	// 부모 틀
	// 추상메서드 (메서드 틀)
	// 리턴값없음  insertMember(MemberDTO memberDTO) 메서드정의
	public void insertMember(MemberDTO memberDTO);
	//MemberDTO memberDTO2=memberService.userCheck(memberDTO);
	public MemberDTO userCheck(MemberDTO memberDTO);
}
