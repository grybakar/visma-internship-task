package com.training.vismainternshiptask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timeStamp;
  private HttpStatus httpStatus;
  private String message;
  private String path;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Map<String, List<String>> validationErrors;

  public ErrorResponse(LocalDateTime timeStamp, HttpStatus httpStatus, String message, String path,
    Map<String, List<String>> subErrors) {
    this.timeStamp = timeStamp;
    this.httpStatus = httpStatus;
    this.message = message;
    this.path = path;
    this.validationErrors = subErrors;
  }
}
