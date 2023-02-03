package com.training.vismainternshiptask.controller;

import com.training.vismainternshiptask.dto.MeetingSearchCriteria;
import com.training.vismainternshiptask.model.Meeting;
import com.training.vismainternshiptask.dto.MeetingDto;
import com.training.vismainternshiptask.service.MeetingService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/meetings")
public class MeetingController {

  private final MeetingService meetingService;

  @PostMapping
  public ResponseEntity<MeetingDto> create(@Valid @RequestBody MeetingDto meetingDto) {
    MeetingDto newMeeting = meetingService.createMeeting(meetingDto);
    return new ResponseEntity<>(newMeeting, HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @DeleteMapping
  public void deleteMeeting(@RequestBody Meeting meeting) {
    meetingService.deleteMeeting(meeting);
  }

  @GetMapping
  public ResponseEntity<List<MeetingDto>> FindAllWithFiltering(@RequestBody MeetingSearchCriteria searchCriteria) {
    List<MeetingDto> filteredMeetings = meetingService.findAllWithFilter(searchCriteria);
    return new ResponseEntity<>(filteredMeetings, HttpStatus.FOUND);
  }
}
