package com.levi9.taster.rest.api.exception;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * REST exception handler class.
 * 
 * @author r.horvat
 */
@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(InvalidInputException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
    public Map<String, String> handleInvalidInputConflict(HttpServletRequest request, HttpServletResponse response, InvalidInputException e) throws IOException {
        return e.getMessages();
    }
}
