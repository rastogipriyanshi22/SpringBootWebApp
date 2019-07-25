package com.webapplication.ws.userservice;

import com.webapplication.ws.ui.model.request.UserDetailsRequestModel;
import com.webapplication.ws.ui.model.response.UserRest;

public interface UserService {
 UserRest createUser(UserDetailsRequestModel userDetails);
}
