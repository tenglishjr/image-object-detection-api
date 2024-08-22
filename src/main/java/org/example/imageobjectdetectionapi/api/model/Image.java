package org.example.imageobjectdetectionapi.api.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Image {
    private UUID id;

    public Image() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                '}';
    }

}
