package com.baseballscoremanagement.api.domain.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * チーム情報を格納するDTO
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Team {
  private int id;
  private final String name;
}
