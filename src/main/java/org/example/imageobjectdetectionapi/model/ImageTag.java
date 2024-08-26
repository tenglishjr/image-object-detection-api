package org.example.imageobjectdetectionapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "image_tags")
public class ImageTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "tag")
    private String tag;

    @Column(name = "confidence")
    private Double confidence;

    public ImageTag() {
    }

    public ImageTag(Long id, Long imageId, String tag, Double confidence) {
        this.id = id;
        this.imageId = imageId;
        this.tag = tag;
        this.confidence = confidence;
    }

    public Long getId() {
        return id;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getTag() {
        return tag;
    }

    public Double getConfidence() {
        return confidence;
    }

}
