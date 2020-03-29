package com.imdb.users.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.imdb.users.model.UserModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

  private UserModel user;
  private String jwtToken;
}
