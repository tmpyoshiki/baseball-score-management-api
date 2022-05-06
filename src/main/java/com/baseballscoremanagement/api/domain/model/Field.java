package com.baseballscoremanagement.api.domain.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 野球場情報を格納するDTO
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Field {
  /**
   * 野球場ID
   */
  private int id;
  /**
   * 野球場名
   */
  private final String name;
}
