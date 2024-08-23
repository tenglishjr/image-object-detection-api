package org.example.imageobjectdetectionapi.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_uploaded", nullable = false)
    private Date dateUploaded;

    @Column(name = "label")
    private String label;

    @Column(name = "tags")
    private String tags;

    public Image() {
    }

    public Image(Long id, Date dateUploaded, String label, String tags) {
        this.id = id;
        this.dateUploaded = dateUploaded;
        this.label = label;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public String getLabel() {
        return label;
    }

    public String getTags() {
        return tags;
    }

}
