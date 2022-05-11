package com.hencarvalho.freightsystem.application.exceptions;

/** Classe que representa a exception de quando um ususario ja existe no sistema. */
public class CustomerCreationException extends RuntimeException {
  public CustomerCreationException(String message) {
    super(message);
  }
}
