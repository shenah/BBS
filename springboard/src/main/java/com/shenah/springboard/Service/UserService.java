package com.shenah.springboard.Service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UserService {

	//아이디 중복 체크 메소드 
	public String idcheck(String Email);
	
	//회원가입 메소드 
	public void register(MultipartHttpServletRequest request);
}
