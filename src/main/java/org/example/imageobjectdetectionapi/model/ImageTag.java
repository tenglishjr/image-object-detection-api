package org.example.imageobjectdetectionapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "image_tags")
public class ImageTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "image")
//	private Image image;

	@Column(name = "tag")
	private String tag;

	@Column(name = "confidence", scale = 3)
	private BigDecimal confidence;

	public ImageTag() {
	}

	public ImageTag(Long id, String tag, BigDecimal confidence) {
		this.id = id;
		this.tag = tag;
		this.confidence = confidence;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public BigDecimal getConfidence() {
		return confidence;
	}

	public void setConfidence(BigDecimal confidence) {
		this.confidence = confidence.setScale(3, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public String toString() {
		return "ImageTag{" +
				"id=" + id +
				", tag='" + tag + '\'' +
				", confidence=" + confidence +
				'}';
	}

}
