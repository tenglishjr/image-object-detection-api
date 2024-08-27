package org.example.imageobjectdetectionapi.service;

import org.example.imageobjectdetectionapi.model.ImageTag;
import org.example.imageobjectdetectionapi.repository.ImageTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageTagService {

    ImageTagRepository repository;

    @Autowired
    public ImageTagService(ImageTagRepository repository) {
        this.repository = repository;
    }

    public void save(ImageTag imageTag) {
        repository.save(imageTag);
    }

}
