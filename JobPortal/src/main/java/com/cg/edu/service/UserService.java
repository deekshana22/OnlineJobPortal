package com.cg.edu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.edu.dto.User;
import com.cg.edu.exceptions.UserNotFoundException;

public interface UserService {

	/**
	 * This is get user by id method which returns user object with matching userId.
	 * @param userId
	 * @return user object.
	 * @throws UserNotFoundException 
	 */
	public User getUserById(Long userId) throws UserNotFoundException;
	
	/**
	 * This is add user method which creates one job application.
	 * @param jobApplication
	 * @return JobApplication object.
	 */
	public User addUser(User userBean);
	
	/**
	 * This is the job apply method which creates one job application.
	 * @param jobApplication
	 * @return JobApplication object.
	 */
	public User updateUser(User userBean);
	
	/**
	 * This is the job apply method which creates one job application.
	 * @param jobApplication
	 * @return JobApplication object.
	 * @throws UserNotFoundException 
	 */
	public List<User> deleteUser(Long userId) throws UserNotFoundException;
	
	/**
	 * This is get all user method which returns list of all the registered users.
	 * @return list of user object.
	 * @throws UserNotFoundException
	 */
	public List<User> getAllUser() ;
	
	/**
	 * This is login method for existing user.
	 * @param id.
	 * @param password.
	 * @return string message.
	 * @throws UserNotFoundException 
	 */
	public User loginUser(String userEmail, String password) ;
	
}
