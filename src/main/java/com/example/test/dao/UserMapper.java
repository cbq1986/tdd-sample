package com.example.test.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.test.domain.User;

@Mapper
public interface UserMapper {
    int insert(User user);
}
