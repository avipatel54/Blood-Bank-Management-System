package com.avi.bloodbank.controlller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.avi.bloodbank.entity.Donor;
import com.avi.bloodbank.entity.DonorForgot;
import com.avi.bloodbank.entity.DonorLogin;
import com.avi.bloodbank.entity.DonorOtp;
import com.avi.bloodbank.entity.DonorTemp;
import com.avi.bloodbank.service.DonorService;

@Controller
@RequestMapping("/donor")
public class DonorController {

	@Autowired
	private DonorService donorService;
	
	@Autowired
	private JavaMailSender sender;

	DonorTemp thedonor;
	
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
	
	@RequestMapping("/SignIn")
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
		return "redirect:/donor/SignIn";
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
		 return "edit";
	 }
	 
	 @RequestMapping("/forgot")
	 
	 public String donorForgot(@ModelAttribute("forgotcheck") DonorForgot theDonorForgot) {
		 if(donorService.forgot(theDonorForgot)=="valid") {
			 
			 String donor1=donorService.sendEmail(theDonorForgot);
			 MimeMessage message = sender.createMimeMessage();
			 MimeMessageHelper helper = new MimeMessageHelper(message);

				try {
					helper.setTo(theDonorForgot.getEmail());
					helper.setText("your Password is :"+donor1);
					helper.setSubject("Mail From Spring Boot");
				} catch (MessagingException e) {
					e.printStackTrace();
					return "Error while sending mail ..";
				}
				sender.send(message);
				return "redirect:/donor/First";
			 
		 }
		 return "redirect:/donor/Forget";
	 }
	 
	 @RequestMapping("/otp")
	 public String otp(@ModelAttribute("donor") DonorTemp theDonorTemp) {
		 int a=donorService.genRandom();
		 thedonor=donorService.donorTemp(theDonorTemp);
		 MimeMessage message = sender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message);

			try {
				helper.setTo(theDonorTemp.getEmail());
				helper.setText("your otp is " + a);
				helper.setSubject("Mail From Spring Boot");
			} catch (MessagingException e) {
				e.printStackTrace();
				return "Error while sending mail ..";
			}
			sender.send(message);
			return "otp";
		
	 }
	 
	 @RequestMapping("/otpverify") 
	 public String otpVerify(@ModelAttribute("otp") DonorOtp theDonorOtp) {
		
		 if(donorService.otpVerification(theDonorOtp)=="valid"){
			 donorService.sendData(thedonor);
			 return "redirect:/donor/First";
		 }
		 return "redirect:/donor/otp";
	 }
	 
	 
	
}
