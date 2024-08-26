package org.example.imageobjectdetectionapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImaggaRO {
    @JsonProperty("result")
    private Result result;
    @JsonProperty("status")
    private Status status;
}
