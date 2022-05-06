package com.baseballscoremanagement.api.interfaces.v1.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

/**
 * 野球場情報のレスポンス用DTO
 */
@AllArgsConstructor
public class FieldResponse {
  /**
   * 野球場ID
   */
  @JsonProperty("id")
  private int id;

  /**
   * 野球場名
   */
  @JsonProperty("name")
  private final String name;
}

