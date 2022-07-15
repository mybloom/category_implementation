package com.jungeun.category.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse<String>> handleBusinessException(
		BusinessException exception) {
		return ResponseEntity.status(exception.getHttpStatus())
			.body(new ErrorResponse<>(exception.getBodyMessage()));
	}
}
