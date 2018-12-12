package com.pk.springboard.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pk.springboard.dao.UserDao;
import com.pk.springboard.domain.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private MailSender mailSender;
	@Override
	public void sendmail(HttpServletRequest request) {
		String setfrom = "ggangpae3@naver.com";         
	    String tomail  = request.getParameter("receiver");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 보내는 사람 이메일
	    String content = request.getParameter("contents");    // 보내는 사람 이메일
	   
	    try {
	      SimpleMailMessage message = new SimpleMailMessage();
	      message.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	      message.setTo(tomail);     // 받는사람 이메일
	      message.setSubject(title); // 메일제목은 생략이 가능하다
	      message.setText(content);  // 메일 내용
	      mailSender.send(message);
	    } catch(Exception e){
	      System.out.println(e);
	    }
	}

	@Override
	public String address(String loc) {
		String [] ar = loc.split(":");
		String latitude = ar[0];
		String longitude = ar[1];
		String addr = 
				"https://apis.daum.net/local/geo/coord2addr?apikey=0fe5a48c34341dac2f00f36d94165f69&longitude=" + longitude + "&latitude=" + latitude + "&inputCoordSystem=WGS84&output=json";
		String address = "";
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(addr);
			HttpURLConnection conn = 
				(HttpURLConnection)url.openConnection();
			
			if (conn != null) {
				conn.setConnectTimeout(20000);
				conn.setUseCaches(false);
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					InputStreamReader isr = new InputStreamReader(
						conn.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					while (true) {
						String line = br.readLine();
						if (line == null) {
							break;
						}
						sb.append(line);
					}
				}
			}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
					
		String json = sb.toString();
		JSONObject obj = new JSONObject(json);
		address = obj.getString("fullName");
		return address;
	}

	@Autowired
	private UserDao userDao;

	@Override
	public String idcheck(String email) {
		return userDao.idcheck(email);
	}

	@Override
	public String nicknamecheck(String nickname) {
		return userDao.nicknamecheck(nickname);
	}

	@Override
	public int register(MultipartHttpServletRequest request) {
		int result = 0;
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		MultipartFile image = request.getFile("image");

		String uploadPath = request.getRealPath("/userimage");
		// 파일 이름 만들기
		UUID uid = UUID.randomUUID();
		String filename = image.getOriginalFilename();
		User user = new User();
		try {
			if (filename.length() > 0) {
				filename = uid + "_" + filename;
				// 저장된 파일 경로 만들기
				String filepath = uploadPath + "/" + filename;
				// 파일 업로드
				File file = new File(filepath);
				image.transferTo(file);
			} else {
				filename = "noimage.png";
			}
			user.setImage(filename);
			user.setEmail(email);
			user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt(10)));
			user.setNickname(nickname);

			result = userDao.register(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public User login(User user) {
		User loginUser = userDao.login(user);
		if (loginUser != null) {

			if (BCrypt.checkpw(user.getPw(), loginUser.getPw())) {
				loginUser.setPw(null);
			} else {
				loginUser = null;
			}
		}
		return loginUser;
	}

}
