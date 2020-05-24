package com.example.test.service.impl;

import com.example.test.dao.UserMapper;
import com.example.test.domain.User;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public String userAdd(User user) throws Exception {
		if (user.getPassword().length() < 6) {
			throw new Exception("パスワードを6桁以上に入力してください!");
		}
		return String.valueOf(mapper.insert(user));
	}
}
