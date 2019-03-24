package org.cryptobroker.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends Exception {

  private HttpStatus httpStatus;

  public HttpException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
