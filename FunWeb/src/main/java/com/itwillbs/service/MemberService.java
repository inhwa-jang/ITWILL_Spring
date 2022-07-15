package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberDTO;

public interface MemberService {

	public void insertMember(MemberDTO memberDTO);
	
//	MemberDTO memberDTO2=memberService.userCheck(memberDTO);
	public MemberDTO userCheck(MemberDTO memberDTO);
	
//	MemberDTO memberDTO=memberService.getMember(id);
	public MemberDTO getMember(String id);
	
//	memberService.updateMember(memberDTO);
	public void updateMember(MemberDTO memberDTO);
	
//	List<MemberDTO> memberList=memberService.getMemberList();
	public List<MemberDTO> getMemberList();

	public MemberDTO getMemberEmail(String email);
	
}
