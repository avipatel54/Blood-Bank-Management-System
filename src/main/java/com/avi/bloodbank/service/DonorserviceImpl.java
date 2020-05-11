package com.avi.bloodbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avi.bloodbank.dao.DonorRepository;
import com.avi.bloodbank.entity.Donor;
import com.avi.bloodbank.entity.DonorLogin;

@Service
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

	@Override
	public Donor findById(int theId) {
		
		Optional<Donor> result =donorRepository.findById(theId);
		
		Donor theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Donor theDonor) {
		donorRepository.save(theDonor);

	}

	@Override
	public void deleteById(int theId) {
		donorRepository.deleteById(theId);
	}

	

	@Override
	public String login(DonorLogin theDonorLogin) {
		Donor donor=donorRepository.findByEmail(theDonorLogin.getUsername());
		
		if(donor==null){
			
			return "invalid";
		}
		
		return "valid";
	}
}
