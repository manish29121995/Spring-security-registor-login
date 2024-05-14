package com.manish.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manish.entity.Customer;
import com.manish.repo.CustomerRepo;

@RestController

public class CustomerRestController {

 @Autowired
 private CustomerRepo repo;
	
 @Autowired
 private PasswordEncoder pwdEncoder;
	
 @Autowired
 private AuthenticationManager authManager ;
	
 @PostMapping("/login")
 public ResponseEntity<String> loginCheck(@RequestBody Customer customer) {
	    
 UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customer.getCname(), customer.getPwd());
	
 try {
	    Authentication authentication = authManager.authenticate(token);
		  if(authentication.isAuthenticated()) {
	      return new ResponseEntity<String>("Welcome to beautiful world..", HttpStatus.OK);
		 }
	}	
 catch (Exception e) {
	
 }	   
		return new ResponseEntity<String>("Invalid Credentials",HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
		   String encodedPwd = pwdEncoder.encode(customer.getPwd());
		   customer.setPwd(encodedPwd);
		   repo.save(customer);
		return new ResponseEntity<String>("Registration Successfully...", HttpStatus.CREATED);
	}
}
