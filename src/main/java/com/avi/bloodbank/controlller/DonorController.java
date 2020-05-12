package com.avi.bloodbank.controlller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avi.bloodbank.entity.Donor;
import com.avi.bloodbank.entity.DonorForgot;
import com.avi.bloodbank.entity.DonorLogin;
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
	public String signUp(Model theModel) {
		Donor theDonor=new Donor();
		theModel.addAttribute("donor", theDonor);
		return "signup";
	}
	
	@GetMapping("/Forget")
	public String forget() {
		return "forget";
	}
	
	@RequestMapping("/save")
	public String saveDonor(@ModelAttribute("donor") Donor theDonor) {
		donorService.save(theDonor);
		return "redirect:/donor/First";
	}
	
	@RequestMapping("/check")
	public String checkLogin(@ModelAttribute("donorlogin") DonorLogin theDonorLogin,Model theModel) {
		
		if(donorService.login(theDonorLogin)=="valid") {
		
			List<Donor> theDonor1=donorService.dash(theDonorLogin);
			theModel.addAttribute("donor",theDonor1);
			
			
			return "dashboard";
		}
		return "login";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteEmployeeById(@PathVariable("id") int id){
		
		donorService.deleteById(id);
	    return "redirect:/donor/First";
	 }
	
	 @RequestMapping("/edit/{id}")
	 public String editEmployeeById(Model theModel, @PathVariable("id") int id){	
		
		 Donor theDonor=donorService.findById(id);
		 theModel.addAttribute("donor", theDonor);
		 return "signup";
	 }
	 
	 @RequestMapping("/forgot")
	 public String donorForgot(@ModelAttribute("forgotcheck") DonorForgot theDonorForgot) {
		 if(donorService.forgot(theDonorForgot)=="valid") {
			 System.out.println("1");
			 String donor1=donorService.sendEmail(theDonorForgot);
			 System.out.println(donor1);
			 return "redirect:/donor/First";
		 }
		 return "forget";
	 }
	
}
