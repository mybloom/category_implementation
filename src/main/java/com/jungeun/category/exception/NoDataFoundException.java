package com.jungeun.category.exception;

import org.springframework.http.HttpStatus;

public abstract class NoDataFoundException extends BusinessException {

	@Override
	protected HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
