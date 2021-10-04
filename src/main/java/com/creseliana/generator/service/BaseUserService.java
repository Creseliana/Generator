package com.creseliana.generator.service;

import com.creseliana.generator.api.repository.UserRepository;
import com.creseliana.generator.api.service.UserService;
import com.creseliana.generator.dto.UserRegisterRequest;
import com.creseliana.generator.dto.UserResponse;
import com.creseliana.generator.model.User;
import com.creseliana.generator.service.util.ListMapper;
import com.creseliana.generator.service.util.NumberGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BaseUserService implements UserService {
    private static final int AMOUNT = 0;

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public void registerUser(UserRegisterRequest newUser) {
        User user = mapper.map(newUser, User.class);
        user.setCreationDate(LocalDateTime.now());
        user.setLuckyNumber(NumberGenerator.generateNumber(AMOUNT));
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserResponse> getLuckyUsers() {
        return ListMapper.mapList(
                mapper,
                userRepository.findByLuckyNumber(NumberGenerator.generateNumber(AMOUNT)),
                UserResponse.class);
    }
}
