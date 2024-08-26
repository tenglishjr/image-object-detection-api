package org.example.imageobjectdetectionapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Result {
    @JsonProperty("tags")
    private List<TagItem> tags;
    @JsonProperty("status")
    private Status status;
}
