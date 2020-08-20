package com.cg.edu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.edu.dto.*;
import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;
import com.cg.edu.exceptions.AdminNotFoundException;
import com.cg.edu.exceptions.JobProviderNotFoundException;
import com.cg.edu.exceptions.UserNotFoundException;
import com.cg.edu.service.AdminService;
import com.cg.edu.service.EmailService;
import com.cg.edu.service.JobProviderService;
import com.cg.edu.service.UserService;

/**
 * @author Meghana
 * controller class for Admin.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	public static Admin currentAdmin;
	
	@Autowired 
	private UserService userService; //autowiring user service.
	
	@Autowired
	private JobProviderService providerService; //autowiring provider service.
	
	@Autowired
	private EmailService emailService; //autowiring email service.
	
	@Autowired
	AdminService adminService; //autowiring admin service.
	
	/**
	 * Mapping for logging in as admin.
	 * @throws AdminNotFoundException 
	 */
	@GetMapping("/loginAdmin/{adminEmail}/{adminPassword}")
	public ResponseEntity<Admin> loginAmin(@PathVariable String adminEmail, @PathVariable String adminPassword) {
			Admin admin = adminService.loginAdmin(adminEmail, adminPassword);
			 ResponseEntity<Admin> response;
			 if(admin != null) {
				 currentAdmin = admin;
				 logger.info("-----------------------got current job provider session-------------------------");
				 response = new ResponseEntity<Admin>(currentAdmin, HttpStatus.OK);
			 } else {
				 logger.error("-----------------------login failed-------------------------");
				 response = new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
			 }
			 
			return response;
		}
	/**
	 * Mapping for adding admin.
	 */
	@PostMapping("/admin/addAdmin")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
		Admin addedAdmin = adminService.addAdmin(admin);
		return new ResponseEntity<Admin>(addedAdmin,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Mapping for deleting admin by adminId.
	 */
	@DeleteMapping("/admin/delete/{adminId}")
	public ResponseEntity deleteAdmin(@PathVariable long adminId) throws AdminNotFoundException{
		return adminService.deleteAdmin(adminId);
		//return new ResponseEntity<Admin>(addedAdmin,HttpStatus.ACCEPTED)
	}
	
	/**
	 * Mapping for updating admin.
	 */
	@PutMapping("/admin/updateAdmin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException{
		Admin updatedAdmin = adminService.updateAdmin(admin);
		return new ResponseEntity<Admin>(updatedAdmin,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Mapping for displaying admin by adminId.
	 */
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<Admin> getAdmin(@PathVariable long adminId) throws AdminNotFoundException{
		Admin admin = adminService.getAdmin(adminId);
		return new ResponseEntity<Admin>(admin,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Mapping for displaying all admins.
	 */
	@GetMapping("/admin/allAdmins")
	public ResponseEntity<List<Admin>> getAllAdmin(){
		List<Admin> adminList = adminService.getAllAdmin();
		return new ResponseEntity<List<Admin>>(adminList,HttpStatus.ACCEPTED);
	}
	
	
	/**
	 * Mapping for adding user by admin.
	 */
	@PostMapping(path = "/admin/addUser")  
	public ResponseEntity<User> addUser(@RequestBody User bean) {
		ResponseEntity<User> response ;
		try {
			
			 User user = userService.addUser(bean);
			 System.out.println(user);
			 if(user != null) {
				 logger.info("-----------------------new user registered with id: " + user.getUserId() + "-------------------------");
				 response = new ResponseEntity<User>(user, HttpStatus.OK);
				 emailService.sendMail("deekshana77@gmail.com", "JOBS", "Greetings!! \n Congratulations your account has been Added. \n Your credentials are :- email: " + user.getUserEmail() + " password: " + user.getUserPassword());
			 } else {
				 logger.error("-----------------------could not register the user-------------------------");
				 response = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			 }
		} catch(Exception ex) {
			 logger.error("-----------------------could not register the user-------------------------");
			response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		 }
		return response;
	}
	
	
	
	/**
	 * Mapping for updating user by admin.
	 */
	@PostMapping(path = "/updateUser")
	public User updateUser(@RequestBody User userBean) {
		User response = userService.updateUser(userBean);
		return response;
	}
	
	
	
	/**
	 * Mapping for deleting user by admin.
	 */
	@DeleteMapping("/deleteUser/{userId}")
	public List<User> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
		return userService.deleteUser(userId);
	}
	
	
	
	/**
	 * Mapping for finding user with user id .
	 */
	@GetMapping(path = "/getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable long userId) {
		ResponseEntity<User> response;
		User userBean;
		try {
			userBean = userService.getUserById(userId);
			response = new ResponseEntity<User>(userBean,HttpStatus.OK);
		} catch(UserNotFoundException ex) {
			response = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch(NullPointerException exception){
			response = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return response;
	} 
	
	
	/**
	 * Mapping for finding all the users by admin.
	 */
	@GetMapping(path = "/getAllUser")
	public  List<User> getAllUser()  {
		return userService.getAllUser();
	}
	
	
	/**
	 * Mapping for adding job provider by admin.
	 */
	@PostMapping(path = "/admin/addJobProvider") 
	public ResponseEntity<JobProvider> addJobProvider(@RequestBody JobProvider bean) {
		ResponseEntity<JobProvider> response;
		try {
			JobProvider jobProvider = providerService.addJobProvider(bean);
			
			 if(jobProvider != null) {
				 logger.info("-----------------------new job provider registered with id: " + jobProvider.getProviderId() + "-------------------------");
				 response = new ResponseEntity<JobProvider>(jobProvider, HttpStatus.OK);
			 } else {
				 logger.error("-----------------------could not register the job provider-------------------------");
				 response = new ResponseEntity<JobProvider>(HttpStatus.NOT_FOUND);
			 }
		} catch(Exception ex) {
			logger.error("-----------------------could not register the job provider-------------------------");
			response = new ResponseEntity<JobProvider>(HttpStatus.NOT_FOUND);
		}
		 
		return response;
	}
	
	/**
	 * Mapping for updating job provider by admin.
	 */
	@PostMapping(path = "/updateProvider")
	public JobProvider jobProvider(@RequestBody JobProvider bean) {
		JobProvider response = providerService.updateProvider(bean);
		return response;
	}
	
	/**
	 * Mapping for deleting job provider with providerId by admin.
	 */
	@DeleteMapping("/deleteProvider/{providerId}")
	public List<JobProvider> deleteProvider(@PathVariable Long providerId) throws UserNotFoundException, JobProviderNotFoundException {
		return providerService.deleteProvider(providerId);
	}
	
	/**
	 * Mapping for finding  job provider with providerId by admin.
	 */
	@GetMapping(path = "/findJobProviderById/{id}")
	public  JobProvider getAllJobProviders(@PathVariable Long id)  {
		return providerService.getProviderById(id);
	}
	
	/**
	 * Mapping for finding all the job providers by admin.
	 */
	@GetMapping(path = "/findAllJobProvider")
	public  List<JobProvider> getAllJobProviders()  {
		return providerService.getAllProviders();
	}
	
	private static final Logger logger;
	static {
		logger = LoggerFactory.getLogger(AdminController.class);
	}
	
	public static Logger getLogger() {
		return logger;
	}
}
