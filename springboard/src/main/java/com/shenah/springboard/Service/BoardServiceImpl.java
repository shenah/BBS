package com.shenah.springboard.Service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenah.springboard.dao.BoardDao;
import com.shenah.springboard.domain.Board;
import com.shenah.springboard.domain.User;

import oracle.sql.DATE;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired 
	private BoardDao boardDao;

	@Override
	public int write(HttpServletRequest request) {
		//title, content 찾아오기 
		String title =request.getParameter("title");
		String content = request.getParameter("content");
		
		//ip 찾아오기 
		String ip = request.getRemoteAddr();
		
		//email 찾아오기 
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String email = user.getEmail();
				
		Board board = new Board();
		board.setContent(content);
		board.setTitle(title);
		board.setIp(ip);
		board.setEmail(email);
		
		return boardDao.write(board);
	}

	@Override
	public List<Board> list(HttpServletRequest request) {
		//Dao 메소드를 호출하여 결과 가져오기 
		List<Board> list = boardDao.list();
		
		Date date = new Date();
		//년 월 일을 찾아오기 
		String [] today = date.toString().split(" ");
		String year = today[5];
		String month = today[1];
		String day = today[2];
		
		for(Board board : list) {
			
			System.out.println(board.getRegdate());
			
			String myYear = board.getRegdate().toString().substring(24);
			String myMonth =board.getRegdate().toString().substring(4,7);
			String myDay =board.getRegdate().toString().substring(8,10);
			
			if(day.equals(myDay) && month.equals(myMonth) && year.equals(myYear)) {
				board.setDispdate(
						board.getRegdate().toString().substring(11,16));
			}else {
				board.setDispdate(myYear + "-" + myMonth + "-" + myDay);
			}
			
		}
		return list;
	} 
}
