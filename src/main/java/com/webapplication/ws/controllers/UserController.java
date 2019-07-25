package com.webapplication.ws.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.ws.exceptions.UserServiceException;
import com.webapplication.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.webapplication.ws.ui.model.request.UserDetailsRequestModel;
import com.webapplication.ws.ui.model.response.UserRest;
import com.webapplication.ws.userservice.UserService;
import com.webapplication.ws.userservice.imp.UserServiceImp;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	UserService userService;
    
	Map<String,UserRest> userMap;
	
	@GetMapping(path = "/{userId}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		if(true) throw new UserServiceException("User service exception");
		
		
		if(userMap.containsKey(userId)) {
			return new ResponseEntity<UserRest>(userMap.get(userId), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	
	}

	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserRest rest = userService.createUser(userDetails);
		
		return new ResponseEntity<UserRest>(rest, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}",consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		UserRest storedUserDetails = userMap.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		userMap.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		
		userMap.remove(id);
	return ResponseEntity.noContent().build();
	}

}
