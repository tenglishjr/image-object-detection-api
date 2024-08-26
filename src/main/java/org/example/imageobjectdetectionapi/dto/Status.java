package org.example.imageobjectdetectionapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
    @JsonProperty("text")
    private String text;
    @JsonProperty("type")
    private String type;
}
