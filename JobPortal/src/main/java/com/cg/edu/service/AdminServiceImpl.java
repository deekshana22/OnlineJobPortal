package com.cg.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.edu.dao.AdminRepository;
import com.cg.edu.dto.Admin;
import com.cg.edu.dto.User;
import com.cg.edu.exceptions.AdminNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired //autowiring admin repository.
	AdminRepository adminRepository;
	

	/**
	 * Mapping for logging in as admin.
	 */
	@Override
	public Admin loginAdmin(String adminEmail, String adminPassword)  {
      Admin admin = adminRepository.findByAdminEmail(adminEmail);

      if(admin.getAdminPassword().equals(adminPassword.toString())) {
			
		} else {
			admin = null;
		}
		return admin;
	}


	/**
	 * Mapping for adding admin details.
	 */
	@Override
	public Admin addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}


	/**
	 * Mapping for deleting admin with adminId.
	 * throws AdminNotFoundException
	 */
	@Override
	public ResponseEntity deleteAdmin(long adminId) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		Admin admin = adminRepository.getOne(adminId);
		if(admin != null) {
			adminRepository.delete(admin);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else throw new AdminNotFoundException("Admin Not found");
	}


	/**
	 * Mapping for updating admin details.
	 * throws AdminNotFoundException
	 */
	@Override
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		Admin newAdmin = new Admin();
		if(admin != null) {
			newAdmin.setAdminId(admin.getAdminId());
			newAdmin.setAdminEmail(admin.getAdminEmail());
			newAdmin.setAdminName(admin.getAdminName());
			newAdmin.setAdminPassword(admin.getAdminPassword());
			adminRepository.save(newAdmin);
			return newAdmin;
		}
		else throw new AdminNotFoundException("Admin Not found");
	}

	/**
	 * Mapping for displaying admin by adminId.
	 * throws AdminNotFoundException
	 */
	@Override
	public Admin getAdmin(long adminId) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		Admin admin = adminRepository.getOne(adminId);
		if(admin != null) {
			return admin;
		}
		else throw new AdminNotFoundException("Admin Not found");
	}

	/**
	 * Mapping for displaying all admins.
	 */
	@Override
	public List<Admin> getAllAdmin() {
		// TODO Auto-generated method stub
		return adminRepository.findAll();
	}
	

}
