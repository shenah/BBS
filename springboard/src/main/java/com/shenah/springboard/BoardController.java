package com.shenah.springboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.shenah.springboard.Service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
}
