package com.pk.springboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pk.springboard.domain.Board;

public interface BoardService {
	public void register(HttpServletRequest request);
	public List<Board> list();

}

