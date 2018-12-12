package com.pk.springboard.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.pk.springboard")
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	private ModelAndView errorModelAndView(Exception ex) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/error/error");
		modelAndView.addObject("exception", ex);

		return modelAndView;
	}
}
