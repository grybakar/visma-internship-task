package com.training.vismainternshiptask.dto;

import com.training.vismainternshiptask.enums.PersonNumFilterType;
import lombok.Data;

@Data
public class MeetingSearchCriteria {

  private String description;
  private String responsiblePerson;
  private String category;
  private String type;
  private String startDate;
  private String endDate;
  private Integer numPersons;
  private PersonNumFilterType personNumFilterType;
}
