package com.cg.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.edu.dto.Admin;
import com.cg.edu.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserEmail(String userEmail);
}
