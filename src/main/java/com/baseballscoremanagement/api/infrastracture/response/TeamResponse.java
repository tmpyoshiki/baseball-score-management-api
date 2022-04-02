package com.baseballscoremanagement.api.infrastracture.response;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class TeamResponse {
  @Id
  private int id;
  private String name;
}
