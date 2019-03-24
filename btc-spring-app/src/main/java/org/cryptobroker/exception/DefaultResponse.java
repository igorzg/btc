package org.cryptobroker.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class DefaultResponse {
  private Date timestamp;
  private String message;
  private String details;
  private HttpStatus httpStatus;

  public DefaultResponse(String message, String details) {
    this(message, details, HttpStatus.OK);
  }

  public DefaultResponse(String message, String details, HttpStatus httpStatus) {
    this.timestamp = new Date();
    this.message = message;
    this.details = details;
    this.httpStatus = httpStatus;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  @JsonIgnore
  public ResponseEntity<DefaultResponse> getResponseEnity() {
    return new ResponseEntity<>(this, httpStatus);
  }
}
