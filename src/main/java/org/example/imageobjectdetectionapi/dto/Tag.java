package org.example.imageobjectdetectionapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag {
    @JsonProperty("en")
    private String en;

    @Override
    public String toString() {
        return en;
    }

}
