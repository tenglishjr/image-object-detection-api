package org.example.imageobjectdetectionapi.service;

import lombok.RequiredArgsConstructor;
import org.example.imageobjectdetectionapi.model.Image;
import org.example.imageobjectdetectionapi.repository.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImagesRepository imagesRepository;

    public List<Image> findAll() {
        return imagesRepository.findAll();
    }

    public Image findById(Long id) {
        return imagesRepository.findById(id).orElse(null);
    }

    public Image save(Image image) {
        Image savedImage = imagesRepository.save(image);
        return savedImage;
    }
}
