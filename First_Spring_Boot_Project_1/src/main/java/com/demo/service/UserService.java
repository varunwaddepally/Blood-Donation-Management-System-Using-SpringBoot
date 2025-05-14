package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.DAO.UserDAO;
import com.demo.entity.UserInformation;
import com.demo.exception.UserException;

@Service 

/* used to specify it is used for validation(service layer)
 and also used to create bean of user defined class because it is 
 combination of @component annotation
 */

public class UserService {
	@Autowired
	UserDAO userDAO;
	
	public UserInformation userRegistrationData(UserInformation userInformation) {
		List<UserInformation> list=userDAO.selectAllDetails();
		UserInformation user=new UserInformation();
		int count=0;
		for(UserInformation userInformation2:list) {
			if(userInformation2.getEmailid().equals(userInformation.getEmailid())) {
				count++;
			}
		}
		if(count>0) {
			throw new UserException("Email id already exists");
		}else {
			user.setEmailid(userInformation.getEmailid());
		}
		
		int mobilecount=0;
		for(UserInformation userInformation2:list) {
			if(userInformation2.getMobilenumber()==userInformation.getMobilenumber()) {
				mobilecount++;
			}
		}
		if(mobilecount>0) {
			throw new UserException("Mobile NUmber already Exists");
		}
		else {
			user.setMobilenumber(userInformation.getMobilenumber());
		}
		
		int passcount=0;
		for(UserInformation userInformation2:list) {
			if(userInformation2.getPassword().equals(userInformation.getPassword())) {
				passcount++;
			}
		}
		if(passcount>0) {
			throw new UserException("Password already exists");
		}
		else {
			user.setPassword(userInformation.getPassword());
		}
		user.setGender(userInformation.getGender());
		user.setAddress(userInformation.getAddress());
		user.setUsername(userInformation.getUsername());
		return userDAO.insertUserInformationDetails(user); 
	}
	
	public List<UserInformation> allUserDetails(){
		return userDAO.selectAllDetails();
	}
	
	public List<UserInformation> getUserDetailsByUsingMobilenumber(long mobilenumber){
		return userDAO.selectByUsingMobilenumber(mobilenumber);
	}
	
	/*
	 * search by using gender public List<UserInformation>
	 * getUserDetailsByUsingGender(String gender){ return
	 * userDAO.selectByUsingGender(gender); }
	 */
	
	public List<UserInformation> filterallUserDetails(String filterUser){
		return userDAO.filterUserDetails(filterUser);
	}
	
	public boolean deleteUserDetails(String emailid) {
		return userDAO.deleteUserDetailsUsingEmailid(emailid);
	}
	
	public UserInformation updateUserDetails(String emailid) {
		return userDAO.updateDetailsUsingEmailid(emailid);
	}
	
	public void update(UserInformation userInformation){
		userDAO.updateDetailsInDb(userInformation);
	} 
	
	public boolean getDetailsByEmailidAndValidation(String emailid, String password) {
		UserInformation user = userDAO.fetchLoginDetails(emailid,password);
		if(user!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
