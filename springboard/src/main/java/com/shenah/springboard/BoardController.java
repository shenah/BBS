package com.shenah.springboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shenah.springboard.Service.BoardService;
import com.shenah.springboard.domain.Board;
import com.shenah.springboard.domain.User;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "board/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		//해당 페이지에서 일정한 시간 간격으로 로그인 체크
		//로그인 정보가 저장된 session 가져오기 
		User user = (User) session.getAttribute("user");
		//로그인 확인 
		if(user == null) {
			//로그인 안되어
			return "redirect:../user/login";
			
		}else {
			return "board/write";
		}
	}
	
	@RequestMapping(value = "board/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpSession session) {
		//로그인 정보가 저장된 session 가져오기 
				User user = (User) session.getAttribute("user");
				//로그인 확인 
				if(user == null) {
					//로그인 안되어
					return "redirect:../user/login";
					
				}else {
					int result = boardService.write(request);
					if(result == 1) {
						//성공하면 게시판 목록보기 페이지 
						return "redirect:list";
					}else {
						return "redirect:write";
					}
				}
	}
	
	@RequestMapping(value = "board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		List<Board> list = boardService.list(request);
		model.addAttribute("list", list);
		return "board/list";
	}
}
