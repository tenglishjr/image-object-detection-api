package org.example.imageobjectdetectionapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "image_tags")
@Data
@NoArgsConstructor
public class ImageTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tag")
	private String tag;

	@Column(name = "confidence", scale = 3)
	private BigDecimal confidence;
}
