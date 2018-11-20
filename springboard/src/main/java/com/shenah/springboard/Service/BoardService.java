package com.shenah.springboard.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shenah.springboard.domain.Board;

public interface BoardService {

	public int write(HttpServletRequest request);
	public List<Board> list(HttpServletRequest request);
}
