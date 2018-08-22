package com.study.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.spring.dao.AddressDao;
import com.study.spring.dao.UserDao;
import com.study.spring.entity.Address;
import com.study.spring.entity.User;

@Repository
public class AddressDaoImpl extends BaseDaoImpl<Address> implements AddressDao{

}