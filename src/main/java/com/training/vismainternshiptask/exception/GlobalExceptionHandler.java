package com.training.vismainternshiptask.exception;

import com.training.vismainternshiptask.dto.ErrorResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  static final String FIELDS_INVALID_MSG = "One or more field are invalid";

  @ExceptionHandler(VismaException.class)
  protected ResponseEntity<Object> handleVismaException(VismaException exception, WebRequest request) {

    ErrorResponse errorResponse = ErrorResponse.builder()
      .timeStamp(LocalDateTime.now())
      .httpStatus(HttpStatus.BAD_REQUEST)
      .message(exception.getLocalizedMessage())
      .path(request.getDescription(false))
      .build();

    log.error("Validation error: {}", errorResponse);

    return ResponseEntity.badRequest().body(errorResponse);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException exception,
    HttpHeaders headers,
    HttpStatusCode status, WebRequest request) {

    ErrorResponse errorResponse = ErrorResponse.builder()
      .timeStamp(LocalDateTime.now())
      .httpStatus(HttpStatus.BAD_REQUEST)
      .message(FIELDS_INVALID_MSG)
      .path(request.getDescription(false))
      .validationErrors(getFieldErrors(exception))
      .build();

    log.error("Validation error: {}", errorResponse);

    return ResponseEntity.badRequest().body(errorResponse);
  }

  private Map<String, List<String>> getFieldErrors(MethodArgumentNotValidException exception) {

    Map<String, List<String>> fieldErrors = new HashMap<>();

    exception.getBindingResult().getFieldErrors().forEach(fieldError -> {

      if (!fieldErrors.containsKey(fieldError.getField())) {
        fieldErrors.put(fieldError.getField(), new ArrayList<>());
      }
      fieldErrors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
    });

    return fieldErrors;
  }

  // I extracted separate method for building an error message
  // but with more exceptions to handle there would a lot of similar methods

//  private ErrorResponse buildErrorMessage(MethodArgumentNotValidException exception, WebRequest request) {
//    return ErrorResponse.builder()
//      .timeStamp(LocalDateTime.now())
//      .httpStatus(HttpStatus.BAD_REQUEST)
//      .message(FIELDS_INVALID_MSG)
//      .path(request.getDescription(false))
//      .validationErrors(getFieldErrors(exception))
//      .build();
//  }


}
