package com.pk.springboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pk.springboard.domain.User;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public String idcheck(String email) {
		return sqlSession.selectOne("user.idcheck", email);
	}
	
	public String nicknamecheck(String nickname) {
		return sqlSession.selectOne("user.nicknamecheck", nickname);
	}
	
	public int register(User user) {
		return sqlSession.insert("user.register", user);
	}
	
	public User login(User user) {
		return sqlSession.selectOne("user.login", user);
	}

}
