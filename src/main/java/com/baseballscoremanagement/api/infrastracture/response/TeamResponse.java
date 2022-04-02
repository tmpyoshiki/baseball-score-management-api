package com.baseballscoremanagement.api.infrastracture.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class TeamResponse {
  @Id
  private int id;
  private String name;
}
