package com.pk.springboard.service;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.springboard.dao.BoardDao;
import com.pk.springboard.domain.Board;
import com.pk.springboard.domain.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> list() {
		List<Board> list = boardDao.list();
		Calendar cal = Calendar.getInstance();
		Date today = new Date(cal.getTimeInMillis());
		for (Board vo : list) {
			System.out.println(today.toString());
			System.out.println(vo.getRegdate().substring(0, 10));
			if (today.toString().equals(vo.getRegdate().substring(0, 10))) {
				vo.setDispdate(vo.getRegdate().substring(11));
			} else {
				vo.setDispdate(vo.getRegdate().substring(0, 10));
			}
		}
		return list;
	}

	public void register(HttpServletRequest request) {
		// 매개변수가 request일 때는 파라미터를 읽습니다.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String nickname = request.getParameter("nickname");
		User user = (User) request.getSession().getAttribute("user");
		String email = user.getEmail();
		if (title.length() == 0) {
			title = "무제";
		}
		if (content.length() == 0) {
			title = "냉무";
		}
		// 필요한 데이터를 생성
		String ip = request.getRemoteAddr();
		Board vo = new Board();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNickname(nickname);
		vo.setEmail(email);
		vo.setIp(ip);
		// DAO의 메소드를 호출
		boardDao.register(vo);
	}
}
