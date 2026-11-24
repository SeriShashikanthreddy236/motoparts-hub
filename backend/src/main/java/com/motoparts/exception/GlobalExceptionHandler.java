package com.motoparts.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {

		Map<String, Object> response = new HashMap<>();

		response.put("success", false);
		response.put("message", "Access Denied");
		response.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {

		Map<String, Object> response = new HashMap<>();

		response.put("success", false);
		response.put("message", ex.getMessage());
		response.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {

		Map<String, Object> response = new HashMap<>();

		response.put("success", false);
		response.put("message", ex.getMessage());
		response.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception ex) {

		Map<String, Object> response = new HashMap<>();

		response.put("success", false);
		response.put("message", ex.getMessage());
		response.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

		Map<String, Object> response = new HashMap<>();

		response.put("success", false);

		response.put("message", ex.getBindingResult().getFieldError().getDefaultMessage());

		response.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}