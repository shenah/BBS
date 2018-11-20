package com.shenah.springboard.domain;

import java.util.Date;

public class Board {

	private int bno;
	private String title;
	private String content; 
	//시간 사용 여부 고려 : 시간을 사용하는 경우 util.Date 
	//시간을 사용하지 않는 경우 sql.Date 
	private Date regdate;
	private int readcnt;
	private String ip;
	private String email; 
	//작성자 이름 
	private String nickname;
	//작성일을 출력할 때는 regdate를 출력하는 것이 아니고 
	//오늘 작성된 글이면 시간을 이전에 작성될 글은 날짜를 출력 
	private String dispdate;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDispdate() {
		return dispdate;
	}
	public void setDispdate(String dispdate) {
		this.dispdate = dispdate;
	}
	
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", content=" + content + ", regdate=" + regdate + ", readcnt="
				+ readcnt + ", ip=" + ip + ", email=" + email + ", nickname=" + nickname + ", dispdate=" + dispdate
				+ "]";
	}
	
}
