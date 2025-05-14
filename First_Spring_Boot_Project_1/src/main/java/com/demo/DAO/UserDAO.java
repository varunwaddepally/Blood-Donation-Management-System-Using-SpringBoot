package com.demo.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.entity.UserInformation;
import com.demo.repository.UserRepository;
@Repository
public class UserDAO {
	@Autowired
	UserRepository userRepository;
	public UserInformation insertUserInformationDetails(UserInformation userInformation) {
		return userRepository.save(userInformation);
	}
	
	public List<UserInformation> selectAllDetails() {
		return userRepository.findAll();
	}
	
	public List<UserInformation> selectByUsingMobilenumber(long mobilenumber){
		return userRepository.getByMobilenumber(mobilenumber);
	}
	
	public List<UserInformation> filterUserDetails(String filteruser){
		return userRepository.readByUsernameOrGenderOrEmailidOrPasswordOrAddress(filteruser);
	}
	
	/*
	 * select by using gender public List<UserInformation>
	 * selectByUsingGender(String gender){ return
	 * userRepository.findByGender(gender); }
	 */
	
	public boolean deleteUserDetailsUsingEmailid(String emailid) {
		Optional<UserInformation> byId=userRepository.findById(emailid);
		try {
			UserInformation user=byId.get();
			userRepository.delete(user);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public UserInformation updateDetailsUsingEmailid(String emailid) {
		
		Optional<UserInformation> byId=userRepository.findById(emailid);
		try {
			UserInformation user=byId.get();
			return user;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public void updateDetailsInDb(UserInformation userInformation){
		userRepository.save(userInformation);
	}
	
	public UserInformation fetchLoginDetails(String emailid, String password) {
		return userRepository.getByEmailidAndPassword(emailid,password);
	}
	
}
