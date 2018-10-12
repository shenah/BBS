package com.shenah.springboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shenah.springboard.Service.UserService;

@Controller
public class UserController {

	@Autowired 
	private UserService userService; 
	
	@RequestMapping(value="/user/register", method = RequestMethod.GET)
	//단순 page 이동은 리턴없이 생성해도 됩니다.  
	//뷰이름이 user/register가 됩니다. 
	//model은 일반 조회한 후 데이터를 넘겨 줄 때 자주 사용
	public void register(Model model) {	
	
	}
	
	
	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	// RedirectAttributes는 작업을 한 후 결과를 넘겨 줄 때 사용 
	public String reqisterPost(MultipartHttpServletRequest request, RedirectAttributes attr) {
		userService.register(request);
		attr.addFlashAttribute("insert", "success");
		return "redirect:/";
	}
 
	
	
	
}
