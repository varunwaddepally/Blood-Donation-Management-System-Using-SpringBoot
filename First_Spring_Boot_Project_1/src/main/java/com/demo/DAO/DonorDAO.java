package com.demo.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.entity.BloodDonation;
import com.demo.repository.DonorRepository;

@Repository
public class DonorDAO {
	
	@Autowired
	DonorRepository donorRepository;
	
	public void insertDonorDetails(BloodDonation bloodDonation) {
		 donorRepository.save(bloodDonation);
		
	}
	
	public List<BloodDonation> selectDonorByUsingAddressOrBloodgroup(String filter) {
		return donorRepository.selectByAddressOrBloodgroup(filter);
	}
	
	public List<BloodDonation> selectAllDonorDetails(){
		return donorRepository.findAll();
	}
}
