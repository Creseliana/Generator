package com.creseliana.generator.api.service;

import com.creseliana.generator.dto.UserRegisterRequest;
import com.creseliana.generator.dto.UserResponse;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterRequest newUser);

    List<UserResponse> getLuckyUsers();
}
