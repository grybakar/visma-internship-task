package com.training.vismainternshiptask.service;

import com.training.vismainternshiptask.dto.AddPersonDto;
import com.training.vismainternshiptask.exception.VismaException;
import com.training.vismainternshiptask.mapper.AddPersonDtoMapper;
import com.training.vismainternshiptask.model.Meeting;
import com.training.vismainternshiptask.model.Person;
import com.training.vismainternshiptask.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;
  private final AddPersonDtoMapper addPersonDtoMapper;
  private final MeetingService meetingService;

  public AddPersonDto addPersonToMeeting(Long personId, Long meetingId) {

    Meeting currentMeeting = meetingService.findById(meetingId);
    Person personToBeAdded = findById(personId);

    if (isPersonAlreadyInMeeting(currentMeeting, personToBeAdded)) {
      throw new VismaException(
        "Person: '%s' is already in meeting: '%s'".formatted(personToBeAdded.getName(), currentMeeting.getName()));
    } else {
      currentMeeting.addPerson(personToBeAdded);
    }
    return addPersonDtoMapper.toAddPersonDto(personToBeAdded);
  }

  public Person findById(Long personId) {
    return personRepository
      .findById(personId)
      .orElseThrow(() -> new VismaException("Person with id: [%d] not found".formatted(personId)));
  }

  public Boolean isPersonAlreadyInMeeting(Meeting meeting, Person person) {
    return meeting.getPersons().contains(person);
  }

  public void removePerson(Long personId, Long meetingId) {

    Meeting currentMeeting = meetingService.findById(meetingId);
    Person personToBeRemoved = findById(personId);

    if (isPersonResponsibleForMeeting(currentMeeting, personToBeRemoved)) {
      throw new VismaException(
        "Person: [%s] is responsible for this meeting!".formatted(personToBeRemoved.getName()));
    } else {
      currentMeeting.removePerson(personToBeRemoved);
    }
  }

  public Boolean isPersonResponsibleForMeeting(Meeting meeting, Person person) {
    return meeting.getResponsiblePerson().equalsIgnoreCase(person.getName());
  }

}




