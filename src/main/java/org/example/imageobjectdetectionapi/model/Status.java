package org.example.imageobjectdetectionapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

	@JsonProperty("text")
	private String text;

	@JsonProperty("type")
	private String type;

}
