package com.example.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.test.dao.UserMapper;
import com.example.test.domain.User;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserServiceImpl.class)
@WebMvcTest(UserService.class)
public class UserServiceTest {

	@Autowired
	UserService userService;

	@MockBean
	UserMapper userMapper;

	@Before
	public void setUp() {
//        this.userService = new UserServiceImpl(userMapper);
	}

	@Test
	public void user_add_OK() throws Exception {
		User user = new User("Tom", "123456");

		when(userMapper.insert(any(User.class))).thenReturn(1);

		assertThat(userService.userAdd(user)).isEqualTo("1");
	}

	@Rule  
	public ExpectedException expectedEx = ExpectedException.none(); 
	
	@Test
	public void user_add_Exception() throws Exception {
		User user = new User("Tom", "12345");
		expectedEx.expect(Exception.class);  
	    expectedEx.expectMessage("パスワードを6桁以上に入力してください!");  
		userService.userAdd(user);
	}

}
