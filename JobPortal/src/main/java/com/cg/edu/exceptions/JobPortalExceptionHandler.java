package com.cg.edu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is global exception handler class.
 */

@ControllerAdvice
public class JobPortalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle custom user exception
	 */
	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<Object> handleUserException() {
		return new ResponseEntity<Object>("No user found.", HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handle exception
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException() {
		return new ResponseEntity<Object>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
