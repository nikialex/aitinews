package com.aitinews.users.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.aitinews.config.security.PasswordEncoder;
import com.aitinews.config.security.SecurityConstants;
import com.aitinews.exceptions.HttpUnauthorizedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.aitinews.users.entities.User;
import com.aitinews.users.entities.UserRepository;
import com.aitinews.users.model.UserModel;
import com.aitinews.users.rest.LoginResponse;
import com.aitinews.users.service.UserService;
import com.aitinews.users.service.converters.UserConverter;

import java.util.Date;
import java.util.Optional;

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

    @Override
    public User getUserById(final String id) {

        final Optional<User> userOpt = userRepository
                .findById(id);
        return userOpt.orElse(null);
    }


}
