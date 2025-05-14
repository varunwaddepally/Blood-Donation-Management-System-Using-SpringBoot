package com.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	//@ResponseBody
	@ExceptionHandler(UserException.class)
	/*
	 * Used to handle the Exceptions occurred in entire application
	 * 
	 * If testController gets any exception it is forwarded/sends request 
	 * to class which consists @ControllerAdvice annotation and send back 
	 * the solution as response.  
	 */
	public String userRelatedException(UserException userException,Model model) {
		System.out.println("User related example");
		String exceptionmsg=userException.getException();
		model.addAttribute("exceptionmsg", exceptionmsg);
		return "UserInformation";
	}
	
	@ExceptionHandler(ExceptionClass.class)
	public String donorRelatedException(ExceptionClass exceptionClass,Model model) {
		System.out.println("Donor related Exception");
		String donorexceptionmsg=exceptionClass.getDonorexceptionmsg();
		model.addAttribute("donorexceptionmsg", donorexceptionmsg);
		System.out.println(donorexceptionmsg);
		return "MenuPage";
	}
}
