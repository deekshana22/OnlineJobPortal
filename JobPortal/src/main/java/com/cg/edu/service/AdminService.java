package com.cg.edu.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.edu.dto.Admin;
import com.cg.edu.exceptions.AdminNotFoundException;

public interface AdminService {
	public Admin loginAdmin(String adminEmail, String adminPassword);
	public Admin addAdmin(Admin admin);
	public ResponseEntity deleteAdmin(long adminId) throws AdminNotFoundException;
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException;
	public Admin getAdmin(long adminId) throws AdminNotFoundException;
	public List<Admin> getAllAdmin();
}
