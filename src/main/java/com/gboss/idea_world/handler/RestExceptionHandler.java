package com.gboss.idea_world.handler;


import com.gboss.idea_world.controller.ext.ErrorResponse;
import com.gboss.idea_world.exception.ApplicationException;
import com.gboss.idea_world.exception.AuthenticationFailedException;
import com.gboss.idea_world.exception.AuthorizationFailedException;
import com.gboss.idea_world.exception.EntityNotFoundException;
import com.gboss.idea_world.exception.GenericErrorCode;
import com.gboss.idea_world.exception.InvalidInputException;
import com.gboss.idea_world.exception.RestException;
import com.gboss.idea_world.exception.SlotUnavailableException;
import com.gboss.idea_world.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AuthenticationFailedException.class)
	public final ResponseEntity<ErrorResponse> handleAuthenticationFailedException(AuthenticationFailedException ex, WebRequest request) {
		return new ResponseEntity(errorResponse(ex), UNAUTHORIZED);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public final ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
		return new ResponseEntity(errorResponse(ex), UNAUTHORIZED);
	}

	@ExceptionHandler(AuthorizationFailedException.class)
	public final ResponseEntity<ErrorResponse> handleAuthorizationFailedException(AuthorizationFailedException ex, WebRequest request) {
		return new ResponseEntity(errorResponse(ex), FORBIDDEN);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		return new ResponseEntity(errorResponse(ex), NOT_FOUND);
	}

	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException ex, WebRequest request) {
		return new ResponseEntity(errorResponse(ex), UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(RestException.class)
	public final ResponseEntity<ErrorResponse> handleRestException(RestException ex, WebRequest request) {
		return new ResponseEntity(errorResponse(ex), UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
		return new ResponseEntity(errorResponse(ex), INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidInputException e) {
		return new ResponseEntity(errorResponse(e), HttpStatus.BAD_REQUEST);
	}


	/**
	@ExceptionHandler(SlotUnavailableException.class)
	public ResponseEntity handleSlotUnavailableException() {
		return ResponseEntity
				.badRequest()
				.body(new ErrorResponse().code("ERR_SLOT_UNAVAILABLE").message("Either the slot is already booked or not available"));
	}
	**/
	
	//mark as ExceptionHandler for the class SlotUnavailableException
	//create a method handleSlotUnavailableException with return type of ResponseEntity
		//return http response for bad request with error code and a message

	private ErrorResponse errorResponse(final ApplicationException exc) {
		exc.printStackTrace();
		return new ErrorResponse().code(exc.getErrorCode().getCode()).message(exc.getMessage());
	}

	private ErrorResponse errorResponse(final RestException exc) {
		exc.printStackTrace();
		return new ErrorResponse().code(exc.getErrorCode().getCode()).message(exc.getMessage());
	}

	private ErrorResponse errorResponse(final RuntimeException exc) {
		exc.printStackTrace();

		final StringWriter stringWriter = new StringWriter();
		exc.printStackTrace(new PrintWriter(stringWriter));

		String message = exc.getMessage();
		if (message == null) {
			message = GenericErrorCode.GEN_001.getDefaultMessage();
		}
		return new ErrorResponse().code(GenericErrorCode.GEN_001.getCode()).message(message).rootCause(stringWriter.getBuffer().toString());
	}

	private ErrorResponse errorResponse(final InvalidInputException invalidInputException) {
		invalidInputException.printStackTrace();

		final StringWriter stringWriter = new StringWriter();
		invalidInputException.printStackTrace(new PrintWriter(stringWriter));

		String message = invalidInputException.getMessage();
		if (message == null) {
			message = GenericErrorCode.GEN_001.getDefaultMessage();
		}
		return new ErrorResponse().code(GenericErrorCode.GEN_001.getCode()).message(message).rootCause(stringWriter.getBuffer().toString());
	}

}