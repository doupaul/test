package com.service;

import com.entities.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    User queryById(int id);

    List<User> queryAllUser();

    List<User> queryUserByName(User user);

    User findUser(User user);
}
