package com.training.vismainternshiptask.controller;

import com.training.vismainternshiptask.dto.AddPersonDto;
import com.training.vismainternshiptask.service.PersonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/persons")
@AllArgsConstructor
@OpenAPIDefinition()
public class PersonController {

  private final PersonService personService;

  @Operation(summary = "Add a person to a meeting")
  @Transactional
  @PutMapping("/{personId}/meeting/{meetingId}")
  public ResponseEntity<AddPersonDto> addPersonToMeeting(
    @PathVariable final Long personId,
    @PathVariable final Long meetingId) {

    AddPersonDto addPersonDto = personService.addPersonToMeeting(personId, meetingId);
    return ResponseEntity.ok(addPersonDto);
  }

  @Transactional
  @DeleteMapping("/{personId}/meeting/{meetingId}")
  public ResponseEntity<Void> removePersonFromMeeting(
    @PathVariable final Long personId,
    @PathVariable final Long meetingId) {

    personService.removePerson(personId, meetingId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
