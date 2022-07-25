package com.springboot.resttemplate.service;

import java.util.List;
import java.util.Optional;

import com.springboot.resttemplate.dao.UserDao;
import com.springboot.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
    UserDao userDao;

	public List<User> getAll() {
		return userDao.findAll();
	}

	public Optional<User> getById(long id) {
		return userDao.findById(id);
	}

	public User save(User newUser) {
		return userDao.save(newUser);
	}

	public void delete(long id) {
		userDao.deleteById(id);
	}

}
