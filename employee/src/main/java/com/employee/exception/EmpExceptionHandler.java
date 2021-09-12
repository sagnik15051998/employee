package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmpExceptionHandler {
	
	@ExceptionHandler(EmpException.class)
	public ResponseEntity<ErrorResponseDto> handleException(EmpException e) {
		ErrorResponseDto eresp = new ErrorResponseDto();
		eresp.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(eresp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
		ErrorResponseDto eresp = new ErrorResponseDto();
		eresp.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(eresp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
