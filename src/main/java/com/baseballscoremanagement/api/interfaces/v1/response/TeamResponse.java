package com.baseballscoremanagement.api.interfaces.v1.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private final String name;
}
