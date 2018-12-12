package com.pk.springboard.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			uri = uri.substring(contextPath.length() + 1);
			String query = request.getQueryString();
			
			if (query == null || query.equals("null")) {
				query = "";
			} else {
				query = "?" + query;
			}
			request.getSession().setAttribute("dest", uri + query);
			request.getSession().setAttribute("msg", "로그인을 하셔야 이용할 수 있는 서비스 입니다.");
			response.sendRedirect(contextPath + "/user/login");				
			return false;
		}
		return true;
	}

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}

