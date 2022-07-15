package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	//객체생성
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.itwillbs.member.MemberMapper";

	@Override
	public void insertMember(MemberDTO memberDTO) {
		// 메서드호출
		sqlSession.insert(namespace+".insertMember", memberDTO);
	}

	@Override
	public MemberDTO userCheck(MemberDTO memberDTO) {
		return sqlSession.selectOne(namespace+".userCheck", memberDTO);
	}

	@Override
	public MemberDTO getMember(String id) {
		return sqlSession.selectOne(namespace+".getMember", id);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) {
		sqlSession.update(namespace+".updateMember", memberDTO);
	}

	@Override
	public List<MemberDTO> getMemberList() {
		return sqlSession.selectList(namespace+".getMemberList");
	}

	@Override
	public MemberDTO getMemberEmail(String email) {
		return sqlSession.selectOne(namespace+".getMemberEmail", email);
	}

}
