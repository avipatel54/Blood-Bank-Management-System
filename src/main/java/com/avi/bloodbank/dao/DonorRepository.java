package com.avi.bloodbank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.avi.bloodbank.entity.Donor;

@Repository
public interface DonorRepository extends CrudRepository<Donor,Integer> {
	public Donor findByEmail(String email);
	public Donor findByPwd(String pwd);
	public Donor findById(String id);
	
	
	@Query(value="select id from Donor where email=:donorEmail",nativeQuery=true)
	public List<Donor> findUser(@Param("donorEmail") String email);
	
	@Query(value="select password from Donor where email=:donorEmail",nativeQuery=true)
	public String findEmail(@Param("donorEmail") String email);
}
