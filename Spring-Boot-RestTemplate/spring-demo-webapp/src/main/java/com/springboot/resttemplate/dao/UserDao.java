package com.springboot.resttemplate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.resttemplate.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
