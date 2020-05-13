package com.avi.bloodbank.service;

import java.util.List;



import java.util.Optional;

import org.hibernate.query.Query;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import com.avi.bloodbank.dao.DonorRepository;
import com.avi.bloodbank.entity.Donor;
import com.avi.bloodbank.entity.DonorForgot;
import com.avi.bloodbank.entity.DonorLogin;

@Service
public class DonorserviceImpl implements DonorService {
	
	private DonorRepository donorRepository;
	
	private EntityManager entityManager;
	
	@Autowired
	public DonorserviceImpl(DonorRepository theDonorRepository) {
		donorRepository=theDonorRepository;
	}
	
	
	public DonorserviceImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	
	
	
	@Override
	public List<Donor> findAll() {
		
		return (List<Donor>) donorRepository.findAll();
	}

	@Override
	public Donor findById(int theId) {
		
		Optional<Donor> result =donorRepository.findById(theId);
		
		Donor theDonor = null;
		
		if (result.isPresent()) {
			theDonor = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theDonor;
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
		Donor donor1=donorRepository.findByPwd(theDonorLogin.getPassword());
		if(donor==null || donor1==null){
			
			
			return "invalid";
		}
		
		return "valid";
	}
	
	
	@Override
	public List<Donor> dash(DonorLogin theDonorLogin) {
		List<Donor> theDonor=donorRepository.findUser(theDonorLogin.getUsername());
		return theDonor;
	}
	 
	@Override
	public String forgot(DonorForgot theDonorForgot) {
		Donor donor=donorRepository.findByEmail(theDonorForgot.getEmail());
		if(donor==null) {
			return "invalid";
		}
		return "valid";
	}
	
	public String sendEmail(DonorForgot theDonorForgot) {
		String theDonor=donorRepository.findEmail(theDonorForgot.getEmail());
		return theDonor;
	}
	
}
