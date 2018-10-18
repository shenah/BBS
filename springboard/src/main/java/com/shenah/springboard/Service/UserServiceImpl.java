package com.shenah.springboard.Service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shenah.springboard.dao.UserDao;
import com.shenah.springboard.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserDao userDao;
	@Override
	public String idcheck(String email) {
		
		return userDao.idcheck(email);
	}
	@Override
	public void register(MultipartHttpServletRequest request) {
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		MultipartFile image = request.getFile("image");
		String uploadPath = request.getRealPath("/userimage");
		
		//파일 이름 만들기 
		UUID uid = UUID.randomUUID();
		String filename = image.getOriginalFilename();
		filename = uid + "_" + filename;
		
		//저장된 파일 경로 만들기 
		String filepath = uploadPath + "/" + filename;
		
		User user = new User();
		
		//파일 업로드 
		File file = new File(filepath);
		try {
			image.transferTo(file);
			user.setImage(filename);
			user.setEmail(email);
			user.setPw(BCrypt.hashpw(pw,BCrypt.gensalt()));
			user.setNickname(nickname);
			userDao.register(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	@Override
	public User login(HttpServletRequest request) {
		//파라미터 읽기 
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		User user = userDao.login(email);
		if(user != null) {
			//비밀번호 확인 
			if (BCrypt.checkpw(pw, user.getPw())) {
				//비밀번호 정확함으로 보안 상 pw를 삭제 
				user.setPw(null);
			}else {
				//비밀번호 틀렸으므로 null로 변경 
				user = null;
			}
		}
		return user;
	}


}
