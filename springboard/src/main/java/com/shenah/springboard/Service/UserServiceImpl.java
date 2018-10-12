package com.shenah.springboard.Service;

import java.io.File;
import java.util.UUID;

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
			user.setPw(pw);
			user.setNickname(nickname);
			userDao.register(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}


}
