package com.training.vismainternshiptask.mapper;

import com.training.vismainternshiptask.model.Meeting;
import com.training.vismainternshiptask.dto.MeetingDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class MeetingDtoMapper {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  public MeetingDto toMeetingDto(Meeting meeting) {
    MeetingDto meetingDto = new MeetingDto();
    meetingDto.setId(meeting.getId());
    meetingDto.setName(meeting.getName());
    meetingDto.setResponsiblePerson(meeting.getResponsiblePerson());
    meetingDto.setDescription(meeting.getDescription());
    meetingDto.setCategory(meeting.getCategory());
    meetingDto.setType(meeting.getType());
    meetingDto.setStartDate(meeting.getStartDate().toString());
    meetingDto.setEndDate(meeting.getEndDate().toString());
    return meetingDto;
  }

  public Meeting toMeetingEntity(MeetingDto meetingDto) {
    Meeting meeting = new Meeting();
    meeting.setId(meetingDto.getId());
    meeting.setName(meetingDto.getName());
    meeting.setResponsiblePerson(meetingDto.getResponsiblePerson());
    meeting.setDescription(meetingDto.getDescription());
    meeting.setCategory(meetingDto.getCategory());
    meeting.setType(meetingDto.getType());
    meeting.setStartDate(LocalDateTime.parse(meetingDto.getStartDate(), formatter));
    meeting.setEndDate(LocalDateTime.parse(meetingDto.getEndDate(), formatter));
    return meeting;
  }
}
