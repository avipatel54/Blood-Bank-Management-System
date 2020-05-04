package com.avi.bloodbank.service;

import java.util.List;

import com.avi.bloodbank.entity.Donor;



public interface DonorService {

public List<Donor> findAll();
	
	
	
	public void save(Donor theDonor);
	
	public void deleteById(int id);



	public Donor findById(int theId);
}
