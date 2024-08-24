package org.example.imageobjectdetectionapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_uploaded", nullable = false)
    @CreatedDate
    private LocalDateTime dateUploaded;

    @Column(name = "label")
    private String label;

    @Column(name = "tags")
    private String tags;


    public Image() {
    }

    public Image(Long id, LocalDateTime dateUploaded, String label, String tags) {
        this.id = id;
        this.dateUploaded = dateUploaded;
        this.label = label;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateUploaded() {
        return dateUploaded;
    }

    public String getLabel() {
        return label;
    }

    public String getTags() {
        return tags;
    }

}
