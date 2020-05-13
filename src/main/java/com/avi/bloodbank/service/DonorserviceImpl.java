package com.avi.bloodbank.service;

import java.util.List;


import java.util.Random;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avi.bloodbank.dao.DonorRepository;
import com.avi.bloodbank.entity.Donor;
import com.avi.bloodbank.entity.DonorForgot;
import com.avi.bloodbank.entity.DonorLogin;
import com.avi.bloodbank.entity.DonorOtp;
import com.avi.bloodbank.entity.DonorTemp;
import com.avi.bloodbank.entity.OtpStore;

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
	
	@Override
	public int genRandom() {
		Random random=new Random();
		
		int int_random=10000 +random.nextInt(20000);
		OtpStore.setOtpstore(int_random);
		return int_random;
		
	}
	
	@Override
	public String otpVerification(DonorOtp theDonorOtp) {
		
		if(OtpStore.getOtpstore()==theDonorOtp.getOtp()) {
			return "valid";
		}
		return "invalid";
	}
	
	@Override
	public DonorTemp donorTemp(DonorTemp theDonorTemp) {
		DonorTemp theDonorTemp1=new DonorTemp();
		theDonorTemp1.setName(theDonorTemp.getName());
		theDonorTemp1.setCity(theDonorTemp.getCity());
		theDonorTemp1.setCountry(theDonorTemp.getCountry());
		theDonorTemp1.setBlood_group(theDonorTemp.getBlood_group());
		theDonorTemp1.setEmail(theDonorTemp.getEmail());
		theDonorTemp1.setContact(theDonorTemp.getContact());
		theDonorTemp1.setPwd(theDonorTemp.getPwd());
		return theDonorTemp1;
	}
	
	
	@Override
	public Donor sendData(DonorTemp theDonorTemp) {
		Donor theDonor1=new Donor();
		theDonor1.setName(theDonorTemp.getName());
		theDonor1.setCity(theDonorTemp.getCity());
		theDonor1.setCountry(theDonorTemp.getCountry());
		theDonor1.setContact(theDonorTemp.getContact());
		theDonor1.setBlood_group(theDonorTemp.getBlood_group());
		theDonor1.setEmail(theDonorTemp.getEmail());
		theDonor1.setPwd(theDonorTemp.getPwd());
		donorRepository.save(theDonor1);
		return theDonor1;
	}
	
}
