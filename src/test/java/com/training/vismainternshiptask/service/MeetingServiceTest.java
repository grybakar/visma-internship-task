package com.training.vismainternshiptask.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.training.vismainternshiptask.dto.MeetingDto;
import com.training.vismainternshiptask.enums.Category;
import com.training.vismainternshiptask.enums.Type;
import com.training.vismainternshiptask.exception.VismaException;
import com.training.vismainternshiptask.mapper.MeetingDtoMapper;
import com.training.vismainternshiptask.model.Meeting;
import com.training.vismainternshiptask.model.Person;
import com.training.vismainternshiptask.repository.MeetingCriteriaRepository;
import com.training.vismainternshiptask.repository.MeetingRepository;
import com.training.vismainternshiptask.repository.PersonRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MeetingServiceTest {

  @Mock
  private MeetingRepository meetingRepository;
  @Mock
  private PersonRepository personRepository;
  @Mock
  private MeetingDtoMapper meetingDtoMapper;
  @Mock
  private MeetingCriteriaRepository criteriaRepository;

  @InjectMocks
  private MeetingService meetingService;

  private Meeting meeting;
  private Person person;
  private MeetingDto meetingDto;

  @BeforeEach
  public void setUpMocks() {
    MockitoAnnotations.openMocks(this);
    meetingService = new MeetingService(meetingRepository, personRepository, meetingDtoMapper, criteriaRepository);
  }

  @BeforeEach
  public void setUpData() {

    meeting = Meeting.builder()
      .id(1L)
      .name("Java meetas")
      .responsiblePerson("Antanas Sadauskas")
      .description("testuojam")
      .category(Category.HUB)
      .type(Type.LIVE)
      .startDate(LocalDateTime.of(2023, 1, 20, 10, 0, 0))
      .endDate(LocalDateTime.of(2023, 1, 20, 11, 0, 0))
      .persons(Set.of(new Person(1L, "Antanas Sadauskas", Set.of())))
      .build();

    person = new Person(1L, "Antanas Sadauskas", Set.of(meeting));

    meetingDto = MeetingDto.builder()
      .id(1L)
      .name("Java meetas")
      .responsiblePerson("Antanas Sadauskas")
      .description("testuojam")
      .category(Category.HUB)
      .type(Type.LIVE)
      .startDate("2023-10-12 10:00")
      .endDate("2023-10-12 11:00")
      .build();
  }

  @Test
  void shouldFindById() {
    when(meetingRepository.findById(1L)).thenReturn(Optional.of(meeting));
    Meeting actual = meetingService.findById(1L);
    assertThat(actual).isEqualTo(meeting);
  }

}