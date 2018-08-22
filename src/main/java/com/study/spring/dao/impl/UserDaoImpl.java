package com.study.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.spring.dao.UserDao;
import com.study.spring.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}