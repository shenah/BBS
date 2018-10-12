package com.shenah.springboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shenah.springboard.Service.UserService;

//요청을 받아서 결과를 JSON으로 리턴해 줄 수 있는 JSONController 
//ajax는 JSON, XML파일를 받기에 ajax로 아이디 중복 검사하기 위하여 JSON으로 리턴 

@RestController
public class JSONController {

	@Autowired 
	private UserService userService; 
	
	@RequestMapping(value="user/idcheck", method=RequestMethod.GET)
	public Map<String, Object> idcheck(String email){
		Map<String, Object> map = new HashMap<>();
		String result = userService.idcheck(email);
		map.put("result", result == null);
		return map;
		
	}
	
}
