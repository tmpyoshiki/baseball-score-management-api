package com.baseballscoremanagement.api.domain.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Team {
  private int id;
  private final String name;
}
