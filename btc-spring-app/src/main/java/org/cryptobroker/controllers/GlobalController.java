package org.cryptobroker.controllers;

import org.cryptobroker.exception.DefaultResponse;
import org.cryptobroker.exception.EntityNotFoundException;
import org.cryptobroker.exception.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
@PropertySource("classpath:config.properties")
public class GlobalController extends ResponseEntityExceptionHandler implements ErrorController {


  @Value("${global.message}")
  private String globalMessage;

  @Value("${global.application-name}")
  private String globalApplicationName;

  // Service Status
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DefaultResponse> status() {
    return new DefaultResponse(globalMessage, globalApplicationName).getResponseEnity();
  }

  // Global exception handler
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<DefaultResponse> handleAllExceptions(Exception ex, WebRequest request) {
    return new DefaultResponse(
      ex.getMessage(),
      request.getDescription(false),
      HttpStatus.INTERNAL_SERVER_ERROR
    ).getResponseEnity();
  }

  // Http exception handler
  @ExceptionHandler({HttpException.class, EntityNotFoundException.class})
  public final ResponseEntity<DefaultResponse> handleAllExceptions(HttpException ex, WebRequest request) {
    return new DefaultResponse(
      ex.getMessage(),
      request.getDescription(false),
      ex.getHttpStatus()
    ).getResponseEnity();
  }

  // Not found error
  @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DefaultResponse> error(Exception ex, WebRequest request, HttpServletResponse servletResponse) {
    return new DefaultResponse(
      servletResponse.getStatus() == 404 ? "Service mapping not found" : "Unknown error",
      request.getDescription(false),
      HttpStatus.NOT_FOUND
    ).getResponseEnity();
  }

  @Override
  public String getErrorPath() {
    return "error";
  }

}
