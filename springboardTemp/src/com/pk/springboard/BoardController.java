package com.pk.springboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pk.springboard.domain.Board;
import com.pk.springboard.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(HttpServletRequest request,
			RedirectAttributes rttr){
		boardService.register(request);
		rttr.addFlashAttribute("msg", "게시글 작성에 성공하셨습니다.");
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(Board board, Model model){
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model){
		List<Board> list = boardService.list();
		model.addAttribute("list", list);
	}


}
