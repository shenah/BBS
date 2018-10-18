package com.shenah.springboard.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shenah.springboard.domain.User;

public interface UserService {

	//아이디 중복 체크 메소드 
	public String idcheck(String Email);
	
	//회원가입 메소드 
	public void register(MultipartHttpServletRequest request);
	
	//로그인 메소드 
	public User login(HttpServletRequest request);
}
