package com.imdb.exceptions;

public class HttpForbiddenException extends RuntimeException {

  public HttpForbiddenException(String message) {
    super(message);
  }

  public HttpForbiddenException() {
  }
}
