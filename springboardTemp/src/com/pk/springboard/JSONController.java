package com.pk.springboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pk.springboard.service.UserService;

@RestController
public class JSONController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="user/idcheck", method = RequestMethod.GET)
	public Map<String, Object> idcheck(String email){
		System.out.println(email);
		Map<String, Object>map = 
			new HashMap<String, Object>();
		String result = userService.idcheck(email);
		map.put("result", result == null);
		System.out.println(map);
		return map;
	}
	
	@RequestMapping(value="user/nicknamecheck", method = RequestMethod.GET)
	public Map<String, Object> nicknamecheck(String nickname){
		System.out.println(nickname);
		Map<String, Object>map = 
			new HashMap<String, Object>();
		String result = userService.nicknamecheck(nickname);
		System.out.println(result);
		map.put("result", result == null);
		System.out.println(map);
		return map;
	}
	
	@RequestMapping(value="address")
	public Map<String, Object> address(@RequestParam("loc")String loc){
	System.out.println(loc);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("address", userService.address(loc));
		System.out.println(map);
		return map;
	}

}
