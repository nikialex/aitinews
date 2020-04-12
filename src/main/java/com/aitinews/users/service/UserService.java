package com.aitinews.users.service;


import com.aitinews.users.entities.User;
import com.aitinews.users.model.UserModel;
import com.aitinews.users.rest.LoginResponse;

public interface UserService {

    UserModel registerUser(UserModel model);
    User getUserById(String id);

    LoginResponse loginUser(String username, String password);

}
