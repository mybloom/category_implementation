package com.jungeun.category.exception;

import org.springframework.http.HttpStatus;

public abstract class InvalidException extends BusinessException {

	@Override
	protected HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
