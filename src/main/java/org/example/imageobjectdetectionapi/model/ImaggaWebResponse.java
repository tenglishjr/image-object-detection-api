package org.example.imageobjectdetectionapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImaggaWebResponse {

	@JsonProperty("result")
	private Result result;

	@JsonProperty("status")
	private Status status;

}
