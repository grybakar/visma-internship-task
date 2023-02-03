package com.training.vismainternshiptask.mapper;

import com.training.vismainternshiptask.dto.AddPersonDto;
import com.training.vismainternshiptask.model.Person;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class AddPersonDtoMapper {

  public AddPersonDto toAddPersonDto(Person person) {
    AddPersonDto addPersonDto = new AddPersonDto();
    addPersonDto.setName(person.getName());
    addPersonDto.setAddedAt(LocalDateTime.now());
    return addPersonDto;
  }

}
