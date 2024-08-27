package org.example.imageobjectdetectionapi.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date_uploaded", nullable = false)
	@CreatedDate
	private LocalDateTime dateUploaded;

    @Column(name = "image_url")
    private String imageUrl;

	@Column(name = "label")
	private String label;

	@OneToMany(targetEntity = ImageTag.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "tags_fkey", referencedColumnName = "id")
	private List<ImageTag> imageTags;

	public Image() {
	}

	public Image(Long id, LocalDateTime dateUploaded, String imageUrl, String label, List<ImageTag> imageTags) {
		this.id = id;
		this.dateUploaded = dateUploaded;
		this.imageUrl = imageUrl;
		this.label = label;
		this.imageTags = imageTags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(LocalDateTime dateUploaded) {
		this.dateUploaded = dateUploaded;
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

	public void setLabel(String label) {
		this.label = label;
	}

	public List<ImageTag> getTags() {
		return imageTags;
	}

	public void setTags(List<ImageTag> imageTags) {
		this.imageTags = imageTags;
	}

	@Override
	public String toString() {
		return "Image{" +
				"id=" + id +
				", dateUploaded=" + dateUploaded +
				", imageUrl='" + imageUrl + '\'' +
				", label='" + label + '\'' +
				", imageTags=" + imageTags +
				'}';
	}
}
