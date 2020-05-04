package com.avi.bloodbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.avi.bloodbank.dao.DonorRepository;
import com.avi.bloodbank.entity.Donor;

public class DonorserviceImpl implements DonorService {
	
	private DonorRepository donorRepository;
	
	@Autowired
	public DonorserviceImpl(DonorRepository theDonorRepository) {
		donorRepository=theDonorRepository;
	}
	
	@Override
	public List<Donor> findAll() {
		
		return (List<Donor>) donorRepository.findAll();
	}

	

	public void save(Donor theDonor) {
		

	}

	@Override
	public void deleteById(int id) {
		
	}

}
