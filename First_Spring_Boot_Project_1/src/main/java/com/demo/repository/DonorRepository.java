package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.BloodDonation;

public interface DonorRepository extends JpaRepository<BloodDonation, String>{
	@Query("select blood from BloodDonation blood where blood.address=?1or blood.bloodgroup=?1")
	List<BloodDonation> selectByAddressOrBloodgroup(String filter);
}
