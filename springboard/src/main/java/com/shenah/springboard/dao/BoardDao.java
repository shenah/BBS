package com.shenah.springboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shenah.springboard.domain.Board;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int write(Board board) {
		return sqlSession.insert("board.write", board);
	}
	
	public List<Board> list(){
		return sqlSession.selectList("board.list");
	}
	
}
