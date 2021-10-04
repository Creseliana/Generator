package com.creseliana.generator.controller;

import com.creseliana.generator.api.service.GeneratorService;
import com.creseliana.generator.api.service.UserService;
import com.creseliana.generator.dto.UserRegisterRequest;
import com.creseliana.generator.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.creseliana.generator.util.TestUtils.jsonBody;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GeneratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private GeneratorService generatorService;

    @Test
    void generateNumber() throws Exception {
        int number = 10;
        when(generatorService.generateNumber(anyInt())).thenReturn(number);

        mockMvc.perform(get("/gen/num?limit=20"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(number)));

        verify(generatorService).generateNumber(anyInt());
        verifyNoMoreInteractions(generatorService);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -20, -1, 0})
    void generateNumberInvalidParamStatus500(int limit) throws Exception {
        mockMvc.perform(get("/gen/num")
                        .param("limit", String.valueOf(limit)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void registerUser() throws Exception {
        mockMvc.perform(post("/gen")
                        .content(jsonBody("__files/user-reg-req.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userService).registerUser(any(UserRegisterRequest.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    void registerUserInvalidJsonStatus500() throws Exception {
        mockMvc.perform(post("/gen")
                        .content(jsonBody("__files/user-reg-req-bad.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        mockMvc.perform(post("/gen")
                        .content(jsonBody("__files/user-reg-req-bad1.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verifyNoInteractions(userService);
    }

    @Test
    void getLuckyUsers() throws Exception {
        UserResponse user = new UserResponse(1L, "user");
        List<UserResponse> users = Collections.singletonList(user);

        when(userService.getLuckyUsers()).thenReturn(users);

        mockMvc.perform(get("/gen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().json(jsonBody("__files/user-res-list.json")));

        verify(userService).getLuckyUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    void getLuckyUsersReturnEmptyList() throws Exception {
        when(userService.getLuckyUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/gen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(userService).getLuckyUsers();
        verifyNoMoreInteractions(userService);
    }
}