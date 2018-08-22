package com.study.spring.service;

import java.util.List;

import com.study.spring.entity.User;

/**
 * 用户管理service接口
 * @author xxx
 *
 */
public interface UserService {
    /**
     * 获取所有的用户信息
     */
    List<User> getAllUser();

	User find(String id);

	void save(User user);

	void update(User user);

    void deleteUser(User user);
}