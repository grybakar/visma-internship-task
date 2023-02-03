package com.training.vismainternshiptask.service;

import com.training.vismainternshiptask.dto.AddPersonDto;
import com.training.vismainternshiptask.dto.MeetingSearchCriteria;
import com.training.vismainternshiptask.exception.VismaException;
import com.training.vismainternshiptask.mapper.AddPersonDtoMapper;
import com.training.vismainternshiptask.mapper.MeetingDtoMapper;
import com.training.vismainternshiptask.model.Meeting;
import com.training.vismainternshiptask.dto.MeetingDto;
import com.training.vismainternshiptask.model.Person;
import com.training.vismainternshiptask.repository.MeetingCriteriaRepository;
import com.training.vismainternshiptask.repository.MeetingRepository;
import com.training.vismainternshiptask.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MeetingService {

  private final MeetingRepository meetingRepository;
  private final PersonRepository personRepository;
  private final MeetingDtoMapper dtoMapper;
  private final MeetingCriteriaRepository criteriaRepository;

  public Meeting findById(Long meetingId) {
    return meetingRepository
      .findById(meetingId)
      .orElseThrow(() -> new VismaException("No meeting found by id: [%d]".formatted(meetingId)));
  }

  public MeetingDto createMeeting(MeetingDto meetingDto) {
    Meeting meeting = dtoMapper.toMeetingEntity(meetingDto);
    Meeting createdMeeting = meetingRepository.save(meeting);
    addResponsiblePersonToCreatedMeeting(createdMeeting);
    log.info("New meeting created: {}, by: {} ", createdMeeting.getName(), createdMeeting.getResponsiblePerson());
    return dtoMapper.toMeetingDto(createdMeeting);
  }

  public void addResponsiblePersonToCreatedMeeting(Meeting meeting) {
    Person responsiblePerson = personRepository
      .findByNameIgnoreCase(meeting.getResponsiblePerson())
      .orElseThrow(() -> new VismaException("Responsible person not found"));
    meeting.addPerson(responsiblePerson);
  }

  public void deleteMeeting(Meeting meeting) {
    log.info("Deleting a meeting withd id: {}", meeting.getId());

    if (isResponsible(meeting.getId(), meeting.getResponsiblePerson())) {
      meetingRepository.deleteById(meeting.getId());
    }
  }

  public boolean isResponsible(Long id, String name) {
    Optional<Meeting> optionalMeeting = meetingRepository.findById(id);
    if (optionalMeeting.isEmpty()) {
      throw new VismaException("No Meeting found by given Id");
    }
    if (!optionalMeeting.get().getResponsiblePerson().equalsIgnoreCase(name)) {
      throw new VismaException("Given person is not responsible for this meeting");
    }
    return true;
  }

  public List<MeetingDto> findAllWithFilter(MeetingSearchCriteria searchCriteria) {
    log.info("Finding meetings with given filters: {}", searchCriteria);
    List<Meeting> filteredMeetings = criteriaRepository.findAllWithFilter(searchCriteria);
    if (filteredMeetings.isEmpty()) {
      throw new VismaException("No Meetings found by given search criteria");
    }
    return filteredMeetings.stream()
      .map(dtoMapper::toMeetingDto)
      .toList();
  }


}
