package com.creseliana.generator.repository;

import com.creseliana.generator.api.repository.UserRepository;
import com.creseliana.generator.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//    }

    @Test
    public void save() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("user");
        user.setCreationDate(LocalDateTime.now());
        user.setLuckyNumber(13);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals(user, savedUser);
    }

    @Test
    public void findByLuckyNumber() {
        int luckyNumber = 13;

        User user = new User();
        user.setUserId(1L);
        user.setUsername("user");
        user.setCreationDate(LocalDateTime.now());
        user.setLuckyNumber(luckyNumber);

        User user1 = new User();
        user1.setUserId(1L);
        user1.setUsername("user");
        user1.setCreationDate(LocalDateTime.now());
        user1.setLuckyNumber(10);

        List<User> users = Arrays.asList(user, user1);

        userRepository.saveAll(users);

        users = userRepository.findByLuckyNumber(luckyNumber);

        assertEquals(1, users.size());
        assertEquals(luckyNumber, users.get(0).getLuckyNumber());
    }
}
