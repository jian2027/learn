package com.study.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.spring.dao.UserDao;
import com.study.spring.entity.User;
import com.study.spring.service.UserService;



@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        List<User> tuList = userDao.find("from User t");
        return tuList;
    }

    @Override
	public void save(User user) {
		userDao.save(user);
    }

	@Override
	public void update(User user) {
		userDao.update(user);
	}
	@Override
	public void deleteUser(User user) {
		userDao.delete(user);		
	}

	@Override
	public User find(String id) {
		return userDao.get(User.class, id);
	}
}