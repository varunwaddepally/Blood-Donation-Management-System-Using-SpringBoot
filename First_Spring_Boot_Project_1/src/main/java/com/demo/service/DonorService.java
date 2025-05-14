package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.DAO.DonorDAO;
import com.demo.entity.BloodDonation;
import com.demo.exception.ExceptionClass;
import com.demo.exception.UserException;

@Service
public class DonorService {
	
	@Autowired
	DonorDAO donorDAO;

	public void donorRegistrationData(BloodDonation bloodDonation) {
		List<BloodDonation> list=donorDAO.selectAllDonorDetails();
		BloodDonation donor=new BloodDonation();
		donor.setName(bloodDonation.getName());
		donor.setBloodgroup(bloodDonation.getBloodgroup());
		donor.setAge(bloodDonation.getAge());
		donor.setGender(bloodDonation.getGender());
		donor.setAddress(bloodDonation.getAddress());
		donor.setEmailid(bloodDonation.getEmailid());
		
		int usercount=0;
		for(BloodDonation blood:list) {
			if(blood.getMobilenumber()==bloodDonation.getMobilenumber()) {
				usercount++;
			}
		}
		if(usercount>0) {
			throw new ExceptionClass("Donor already exists with same mobile number");
		}
		else {
		donor.setMobilenumber(bloodDonation.getMobilenumber());
		}
		donorDAO.insertDonorDetails(donor);
	}
	public List<BloodDonation> getDonor(String filter){
		return donorDAO.selectDonorByUsingAddressOrBloodgroup(filter);
	}
	
}
