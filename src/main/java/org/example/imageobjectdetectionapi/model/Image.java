package org.example.imageobjectdetectionapi.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date_uploaded", nullable = false)
	@CreatedDate
	private LocalDateTime dateUploaded;

    @Column(name = "image_url")
    private String imageUrl;

	@Column(name = "label")
	private String label;

	@Column(name = "tags")
	private String tags;

	public Image() {
	}

	public Image(Long id, LocalDateTime dateUploaded, String imageUrl, String label, String tags) {
		this.id = id;
		this.dateUploaded = dateUploaded;
        this.imageUrl = imageUrl;
		this.label = label;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDateUploaded() {
		return dateUploaded;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLabel() {
		return label;
	}

	public String getTags() {
		return tags;
	}

	public void setDateUploaded(LocalDateTime dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
