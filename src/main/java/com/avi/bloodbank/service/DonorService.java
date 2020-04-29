package com.avi.bloodbank.service;

import java.util.List;

import com.avi.bloodbank.entity.Donor;



public interface DonorService {

public List<Donor> findAll();
	
	public Donor findById(int theId);
	
	public void save(Donor theDonor);
	
	public void deleteById(int id);
}
