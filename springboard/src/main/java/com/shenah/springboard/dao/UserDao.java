package com.shenah.springboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shenah.springboard.domain.User;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//select 구문을 수행하는 메소드는 결과가 0개나 1개인지 
	//아니면 2개 이상도 나올 수 있는지를 확인하고 
	//2개 이상 나올 수 있으면 resultType의 List 
	//0개나 1개이면 resultType으로 리턴 타입을 만듭니다. 
	//매개변수가 있으면 매개변수를 만들고 없으면 안 만들면 됩니다. 
	
	//아이디 중복 체크 메소드 
	public String idcheck(String email) {
		return sqlSession.selectOne("user.idcheck", email);
	}
	
	//회원가입 메소드 
	public void register(User user) {
		sqlSession.selectOne("user.register", user);
	}
	
	//로그인 메소드 
	public User login(String email) {
		return sqlSession.selectOne("user.login", email);
	}
}
