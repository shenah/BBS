package com.pk.springboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pk.springboard.domain.User;
import com.pk.springboard.service.UserService;

@Controller
public class UserController {
	@RequestMapping(value = "user/register", method = RequestMethod.GET)
	public void register(Model model){}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	public String registerPost(MultipartHttpServletRequest request,
			RedirectAttributes rttr){
		int result = userService.register(request);
		System.out.print("result:" + result);
		if(result > 0) {
			rttr.addFlashAttribute("insert", "success");
			return "redirect:/";
		}else {
			return "redirect:user/register";
		}
	}
	
	@RequestMapping(value = "user/login", method = RequestMethod.GET)
	public void login(Model model){}


	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String login(User user, HttpSession session, Model model, RedirectAttributes attr) {
		User userVO = userService.login(user);
		System.out.println(userVO);
		if (userVO == null) {
			attr.addFlashAttribute("msg", "없는 이메일이거나 비밀번호가 잘못되었습니다.");
			return "redirect:login";
		}
		session.setAttribute("user", userVO);
		Object dest = session.getAttribute("dest");
		if(dest == null) {
			return "redirect:../";
		}
		else {
			return "redirect:../" + dest.toString();
		}
		
	}
	@RequestMapping(value = "user/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "user/sendmail", method = RequestMethod.GET)
	public String sendmail(@RequestParam("email") String email, Model model){
		model.addAttribute("receiver", email);
		return "user/sendmail";
	}

	@RequestMapping(value = "user/sendmail", method = RequestMethod.POST)
	public String sendmail(HttpServletRequest request, RedirectAttributes attr){
		userService.sendmail(request);
		attr.addFlashAttribute("msg", "메일보내기에 성공했습니다.");
		return "redirect:/";
	}


}
