package com.avi.bloodbank.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avi.bloodbank.entity.Donor;
import com.avi.bloodbank.service.DonorService;

@Controller
@RequestMapping("/donor")
public class DonorController {

	@Autowired
	private DonorService donorService;

	public DonorController(DonorService theDonorService) {
		donorService=theDonorService;
	}
	@GetMapping("/First")
	public String firstPage() {
		return "firstpage";
	}

	@GetMapping("/list")
	public String donorList(Model theModel) {
		List<Donor> theDonor=donorService.findAll();
		theModel.addAttribute("donor",theDonor);
		return "compact-table";
	}
	
	@GetMapping("/SignIn")
	public String signIn() {
		return "login";
	}
	
	@GetMapping("/SignUp")
	public String signUp() {
		return "signup";
	}
	
	@GetMapping("/Forget")
	public String forget() {
		return "forget";
	}
}
