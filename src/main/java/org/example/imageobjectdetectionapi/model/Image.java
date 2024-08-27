package org.example.imageobjectdetectionapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
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
}
