package com.example.controller;

import org.springframework.http.HttpStatus;
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Login;
//import com.example.entity.Roles;
//import com.example.entity.SignUp;
//import com.example.entity.User;
//import com.example.repository.RoleRepository;
//import com.example.repository.UserRepository;
import com.example.response.JwtAuthResponse;
//import com.example.security.JwtTokenProvider;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@Api(value = "Auto controller exposes signIn and signUp REST API's")
@RestController
@RequestMapping("/api/auth/")
public class AuthController {
//
////	@Qualifier("authenticationManagerBean")
////	@Autowired
	private AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private RoleRepository roleRepository;
//	
//	//@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private JwtTokenProvider tokenProvider;
//	
//	@ApiOperation(value = "REST API to Login or SignIn user to Blog App")
	@PostMapping("signIn")
	public ResponseEntity<String> authenticateUser(@RequestBody Login login) {
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				login.getUsernameOrEmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseEntity<>("User signed-in successfully", HttpStatus.OK);
		
		//get token from tokenprovider class
		//String token = tokenProvider.generateToken(authentication);
		
		//return ResponseEntity.ok(new JwtAuthResponse(token));
	}
//	
//	@ApiOperation(value = "REST API to Register or SignUp user to Blog App")
//	@PostMapping("signUp")
//	public ResponseEntity<String> registerUser(@RequestBody SignUp signUp) {
//		
//		//add check for username exists in DB
//		if(userRepository.existsByUsername(signUp.getUsername())) {
//			return new ResponseEntity<>("username alreay taken", HttpStatus.BAD_REQUEST);
//		}
//		
//		//add check for email exists in DB
//		if(userRepository.existsByEmail(signUp.getEmail())) {
//			return new ResponseEntity<>("email alreay exists", HttpStatus.BAD_REQUEST);
//		}
//		
//		//create user object
//		User user = new User();
//		user.setName(signUp.getName());
//		user.setEmail(signUp.getEmail());
//		user.setUsername(signUp.getUsername());
//		user.setPassword(passwordEncoder.encode(signUp.getPassword()));
//		
//		Roles roles = roleRepository.findByName("ROLE_ADMIN").get();
//		user.setRoles(Collections.singletonList(roles));
//		
//		userRepository.save(user);
//		
//		return new ResponseEntity<>("User regstered successfully", HttpStatus.OK);
//	}
}
