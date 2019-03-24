package org.cryptobroker.exception;

import org.springframework.http.HttpStatus;


public class EntityNotFoundException extends HttpException {

  public EntityNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
