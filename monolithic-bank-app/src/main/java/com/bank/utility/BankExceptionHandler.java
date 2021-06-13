package com.bank.utility;

import java.util.Date;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorId(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setErrorMessage(ex.getMessage());
		errorMessage.setTimeStamp(new Date());

		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<ErrorMessage> handleValidationException(Exception ex) {

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorId(HttpStatus.BAD_REQUEST.value());

		if (ex instanceof MethodArgumentNotValidException) {
			errorMessage.setErrorMessage(((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors()
					.stream().map((e) -> e.getDefaultMessage()).collect(Collectors.joining(",")));
		}
		if (ex instanceof ConstraintViolationException) {
			errorMessage.setErrorMessage(((ConstraintViolationException) ex).getConstraintViolations().stream()
					.map((e) -> e.getMessage()).collect(Collectors.joining(",")));
		}
		errorMessage.setTimeStamp(new Date());

		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
