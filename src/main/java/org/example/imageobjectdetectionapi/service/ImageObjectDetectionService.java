package org.example.imageobjectdetectionapi.service;

import lombok.RequiredArgsConstructor;
import org.example.imageobjectdetectionapi.model.Image;
import org.example.imageobjectdetectionapi.repository.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageObjectDetectionService {

    private final ImagesRepository imagesRepository;

    public Image save(Image image) {
        Image savedImage = imagesRepository.save(image);
        return savedImage;
    }

    public List<Image> findAll() {
        return imagesRepository.findAll();
    }
}
