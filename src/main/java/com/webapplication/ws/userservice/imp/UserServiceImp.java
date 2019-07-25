package com.webapplication.ws.userservice.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapplication.ws.shared.Utils;
import com.webapplication.ws.ui.model.request.UserDetailsRequestModel;
import com.webapplication.ws.ui.model.response.UserRest;
import com.webapplication.ws.userservice.UserService;

@Service
public class UserServiceImp implements UserService{

	Map<String,UserRest> userMap;
	Utils utils;
	
	public UserServiceImp() {}
	@Autowired
	public UserServiceImp(Utils utils) {
		this.utils = utils;
		
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest rest = new UserRest();
		rest.setEmail(userDetails.getEmail());
		rest.setFirstName(userDetails.getFirstName());
		rest.setLastName(userDetails.getLastName());
		
		String userId  = utils.generateUserId();
		rest.setUserId(userId);
		if(userMap == null) userMap = new HashMap<>();
		userMap.put(userId, rest);

		return rest;
	}

}
