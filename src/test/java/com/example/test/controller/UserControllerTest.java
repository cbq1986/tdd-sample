package com.example.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.test.controller.UserController;
import com.example.test.dao.UserMapper;
import com.example.test.domain.User;
import com.example.test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;
    
    @MockBean
    UserMapper userMapper;

    @Test
    public void user_add_returnOK() throws Exception {

        User user = new User("Tom","123");

        when(userService.userAdd(any(User.class))).thenReturn("1");

        String result = mockMvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
                .andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        assertThat(result).isEqualTo("1");
    }

    @Test
    public void user_add_return400() throws Exception {

        User user = new User(null,null);
        mockMvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
                .andDo(print()).andExpect(status().isBadRequest());
    }

}
