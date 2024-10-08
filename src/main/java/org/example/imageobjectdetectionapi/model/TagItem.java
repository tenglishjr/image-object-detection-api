package org.example.imageobjectdetectionapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TagItem {

	@JsonProperty("confidence")
	private BigDecimal confidence;

	@JsonProperty("tag")
	private Tag tag;

}
