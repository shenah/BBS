package com.pk.springboard.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pk.springboard.domain.User;

public interface UserService {
	public String idcheck(String email);
	public String nicknamecheck(String nickname);
	public int register(MultipartHttpServletRequest request);
	public User login(User user);
	public String address(String loc);
	public void sendmail(HttpServletRequest request);


}

