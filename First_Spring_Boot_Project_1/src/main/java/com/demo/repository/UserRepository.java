package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.UserInformation;

public interface UserRepository extends JpaRepository<UserInformation, String>{
	List<UserInformation> getByMobilenumber(long mobilenumber);
	List<UserInformation> findByGender(String gender);
	
	@Query("select user from UserInformation user where user.username=?1or user.gender=?1 or user.emailid=?1 or user.password=?1 or user.address=?1")
	List<UserInformation> readByUsernameOrGenderOrEmailidOrPasswordOrAddress(String filteruser);
	
	@Query("select user from UserInformation user where user.emailid=?1 and user.password=?2")
	UserInformation getByEmailidAndPassword(String emailid, String password);
}
