package be.ordina.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception ex){
		ResponseEntity<String> entity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
		return entity;
		
	}
}
