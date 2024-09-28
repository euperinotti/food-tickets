package br.com.euperinotti.foodtickets.config.errors;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;

@RestControllerAdvice
public class SpringErrorHandler {

  @ExceptionHandler(AppExceptions.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<CustomErrorResponse> handleNotFound(AppExceptions exception,
      WebRequest request) {

    CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
        exception.getMessage());

    return new ResponseEntity<CustomErrorResponse>(response, HttpStatus.NOT_FOUND);
  }

}
