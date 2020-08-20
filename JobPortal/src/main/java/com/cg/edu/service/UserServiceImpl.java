package com.cg.edu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.edu.dao.UserRepository;
import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;
import com.cg.edu.exceptions.UserNotFoundException;
import com.cg.edu.util.ErrorMessageUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger logger;
	static {
		logger = LoggerFactory.getLogger(UserServiceImpl.class);
	}

	public static Logger getLogger() {
		return logger;
	}

	@Autowired
	UserRepository repository;
	
	/**
	 * This is login method for existing user.
	 * @param email.
	 * @param password.
	 * @return string message.
	 */
	@Override
	public User loginUser(String userEmail, String password) {
User user = repository.findByUserEmail(userEmail);
		
		if(user.getUserPassword().equals(password.toString())) {
			
		} else {
			user = null;
		}
		return user;
	}
	
	/**
	 * This is the add user method.
	 * @param User
	 * @return User object.
	 */
	@Override
	public User addUser(User userBean) {
		return repository.save(userBean);
	}

	/**
	 * This is get user by id method which returns user object with matching userId.
	 * @param userId
	 * @return user object.
	 * @throws UserNotFoundException 
	 */
	@Override
	public User getUserById(Long userId) throws UserNotFoundException {
		User user = repository.findById(userId).get();
		if(user.getUserId() != userId) {
			throw new UserNotFoundException(ErrorMessageUtil.FIND_USER_ERROR);
		}
		return user;
	}

	/**
	 * This is the update user method.
	 * @param user
	 * @return user object.
	 */
	@Override
	public User updateUser(User userBean) {
		return repository.save(userBean);
	}

	/**
	 * This is the delete user method.
	 * @param userId
	 * @return list of user object.
	 * @throws UserNotFoundException 
	 */
	@Override
	public List<User> deleteUser(Long userId) throws UserNotFoundException {
		User user = repository.findById(userId).get();
		if(user.getUserId() != userId) {
			throw new UserNotFoundException(ErrorMessageUtil.FIND_USER_ERROR);
		}
		repository.delete(user);
		return getAllUser();
	}

	/**
	 * This is get all user method which returns list of all the registered users.
	 * @return list of user object.
	 * @throws UserNotFoundException 
	 */
    @Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
}
