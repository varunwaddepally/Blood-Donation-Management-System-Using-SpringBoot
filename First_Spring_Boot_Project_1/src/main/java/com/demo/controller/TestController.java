package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entity.BloodDonation;
import com.demo.entity.UserInformation;
import com.demo.service.DonorService;
import com.demo.service.UserService;

@Controller
public class TestController {
	@Autowired
	UserService userService;
	
	@Autowired
	DonorService donorService;
	
	@RequestMapping("/registrationPage")
	public String registrationPage() {
		return "UserInformation";
	}
	
	@RequestMapping("/user")
	public String Registration(UserInformation user,Model model) {
		//inserting data into database
		userService.userRegistrationData(user);
		return "redirect:/alluserDetails";
	}
	
	@RequestMapping("/alluserDetails")
	public String getAllUserDetails(Model model) {
		List<UserInformation> list=userService.allUserDetails();
		
		model.addAttribute("alluserdetails", list);
		return "DisplayAllUserDetails";
	}
	

	@RequestMapping("/userByMobileNumber")
	public String getMobileNumber(@RequestParam("mobileNumber") long mobilenumber, Model model) {
		List<UserInformation> list=userService.getUserDetailsByUsingMobilenumber(mobilenumber);
		model.addAttribute("alluserdetails", list);
		return "DisplayAllUserDetails";
	}
	
	/*
	 * @RequestMapping("/userByGender") public String
	 * getGender(@RequestParam("gender") String gender,Model model) {
	 * List<UserInformation> list=userService.getUserDetailsByUsingGender(gender);
	 * model.addAttribute("alluserdetails", list); return "DisplayAllUserDetails"; }
	 */
	
	@RequestMapping("/userByAnyAttribute")
	public String getUserDetailsByUsingAnyAttribute(@RequestParam("userNameorgenderoraddressorpasswordoremailId") String filter,Model model){
		List<UserInformation> list=userService.filterallUserDetails(filter);
		model.addAttribute("alluserdetails", list);
		return "DisplayAllUserDetails";
	}
	@RequestMapping("/deleteDetails")
	public String deleteDetails(@RequestParam("emailId") String emailid) {
		if(userService.deleteUserDetails(emailid)) {
			return "redirect:/alluserDetails";
		}
		else {
			return "redirect:/alluserDetails";
		}
	}
	
	  @RequestMapping("/display") 
	  public String getDetails(UserInformation userInformation,Model model) { 
		  userService.update(userInformation);
		  return "redirect:/alluserDetails"; 
	  }
	 
	
	  @RequestMapping("/updateDetails") 
	  public String updateDetails(@RequestParam("emailid") String emailid, Model model) {
		UserInformation user=userService.updateUserDetails(emailid);
	  	model.addAttribute("user",user); 
	  	return "FetchUserDetailsForUpdating"; 
	  }
	 
	  @RequestMapping("/userLogin")
	  public String userLoginPage() {
		  return "UserLogin";
	  }
	  
	  @RequestMapping("/userLoginValidation")
	  public String loginValidation(@RequestParam("emailid") String emailid, @RequestParam("password") String password, Model model) {
  
		  if(userService.getDetailsByEmailidAndValidation(emailid, password)) {
			  model.addAttribute("email", emailid);
			  return "MenuPage";
		  }  
		  else{
			  return "UserLogin";
		  }
	  }
		
	@RequestMapping("/MenuPage") 
	public String welcomePage()
	{ 
		return "DonorRegistrationPage"; 
	}
		  
	  @RequestMapping("/getDetails")
	  public String getUserDetails(@RequestParam("emailid") String emailid, Model model) {
		UserInformation user = userService.updateUserDetails(emailid); //used to get details using emailid  
		model.addAttribute("user", user);
		return "DonorRegistrationPage";
	  }
	  
	  @RequestMapping("/insertDonorDetails")
	  public String insertDonorDetails(BloodDonation bloodDonation) {
		  System.out.println(bloodDonation);
		  donorService.donorRegistrationData(bloodDonation);
		  return "ThankYouPage";
	  }
	  @RequestMapping("/DonorDetails")
	  public String donorDetails() {
		  return "DonorDetails";
	  }
	  
	  @RequestMapping("/welcome")
	  public String thankyou() {
		  return "BloodDonationProject";
	  }
	  @RequestMapping("/getDonorDetails")
	  public String getDonorDetails(@RequestParam("addressorbloodgroup") String filter, Model model){
		  List<BloodDonation> blood = donorService.getDonor(filter);
		  model.addAttribute("donor1", blood);
		  return "DonorDetails";
	  }
	  @RequestMapping("/requestPage")
	  public String getRequestPage(@RequestParam("emailid") String emailid, Model model) {
		  model.addAttribute("emailid", emailid);
		  return "requestPage";
	  }
}
