package com.imdb.users.service;

import com.imdb.users.entities.User;
import com.imdb.users.model.UserModel;
import com.imdb.users.rest.LoginResponse;

public interface UserService {

    UserModel registerUser(UserModel model);
    User getUserById(String id);

    LoginResponse loginUser(String username, String password);

}
