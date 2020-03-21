package com.imdb.users.service.impl;


import com.auth0.jwt.JWT;
import java.util.Date;
import java.util.Optional;

import com.auth0.jwt.algorithms.Algorithm;
import com.imdb.exceptions.HttpUnauthorizedException;
import com.imdb.config.security.PasswordEncoder;
import com.imdb.config.security.SecurityConstants;
import com.imdb.users.entities.User;
import com.imdb.users.model.UserModel;
import com.imdb.users.rest.LoginResponse;
import com.imdb.users.service.UserService;
import com.imdb.users.service.converters.UserConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.imdb.users.entities.UserRepository;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  public UserServiceImpl(final UserRepository userRepository,
      final UserConverter userConverter) {
    this.userRepository = userRepository;
    this.userConverter = userConverter;
  }

  @Override
  public UserModel registerUser(final UserModel model) {
    log.info("Register user BEGIN: {}", model);

    model.setPassword(PasswordEncoder.hashPassword(model.getPassword()));

    final User user = userConverter.convertToEntity(model);

    final User saved = userRepository.save(user);

    log.info("Register user END: {}", saved);

    return userConverter.convertToModel(saved);
  }

  @Override
  public LoginResponse loginUser(final String username, final String password) {
    log.info("Login user BEGIN: {}", username);

    final User user = getUser(username);

    if (!PasswordEncoder.checkPassword(password, user.getPassword())) {
      throw new HttpUnauthorizedException();
    }

    final String jwtToken = JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

    final UserModel userModel = userConverter.convertToModel(user);

    final LoginResponse response = new LoginResponse(userModel, jwtToken);

    log.info("Login user END: {}", response);

    return response;
  }

  private User getUser(final String username) {
    final Optional<User> userOpt = userRepository
        .findByUsername(username);

    return userOpt.orElse(null);
  }

}
