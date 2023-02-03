package com.training.vismainternshiptask.enums;

import lombok.Getter;

@Getter
public enum Type {

  LIVE("live"),
  IN_PERSON("inPerson");

  private final String type;

  Type(String type) {
    this.type = type;
  }

}
