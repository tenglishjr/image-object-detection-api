package org.example.imageobjectdetectionapi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.imageobjectdetectionapi.exception.ImageNotFoundException;
import org.example.imageobjectdetectionapi.exception.ImaggaBadRequestException;
import org.example.imageobjectdetectionapi.model.ImageRequest;
import org.example.imageobjectdetectionapi.model.ImaggaWebResponse;
import org.example.imageobjectdetectionapi.entity.Image;
import org.example.imageobjectdetectionapi.repository.ImagesRepository;
import org.example.imageobjectdetectionapi.util.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ImageService {

    private final ImagesRepository imagesRepository;

    private ImaggaService imaggaService;

    @Autowired
    public ImageService(ImagesRepository imagesRepository, ImaggaService imaggaService) {
        this.imagesRepository = imagesRepository;
        this.imaggaService = imaggaService;
    }

    public List<Image> findAll() {
        return imagesRepository.findAll();
    }

    public Image findById(Long id) {
        Image image = imagesRepository.findById(id).orElse(null);
        if (image == null) {
            throw new ImageNotFoundException("Image with id of \"" + id + "\" not found");
        }
        return image;
    }

    public List<Image> findAllWithObjects(String objects) {
        String[] tags = objects.toLowerCase().replace("\"", "").split(",");
        return imagesRepository.findAllByTags(tags);
    }

    public Image saveImage(ImageRequest imageRequest) {
        try {
            ImaggaWebResponse imaggaWebResponse = null;
            if (imageRequest.isUseObjectDetection()) {
                imaggaWebResponse = imaggaService.getObjectDetection(imageRequest);
            }
            Image image = ImageMapper.mapToImage(imaggaWebResponse, imageRequest);
            return imagesRepository.save(image);
        } catch (ImaggaBadRequestException e) {
            log.error("The request sent to Imagga was invalid; check the image URL used to make the request");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
