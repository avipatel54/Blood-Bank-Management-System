package com.avi.bloodbank.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import com.avi.bloodbank.entity.Donor;

@Repository
public interface DonorRepository extends CrudRepository<Donor,Integer> {
	public Donor findByEmail(String email);
}
