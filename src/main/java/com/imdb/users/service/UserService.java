package com.imdb.users.service;

import com.imdb.users.model.UserModel;
import com.imdb.users.rest.LoginResponse;

public interface UserService {

    UserModel registerUser(UserModel model);

    LoginResponse loginUser(String username, String password);

}
