package com.shenah.springboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenah.springboard.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired 
	private BoardDao boardDao; 
}
