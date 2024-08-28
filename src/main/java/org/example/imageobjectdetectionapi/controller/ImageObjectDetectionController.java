package org.example.imageobjectdetectionapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.imageobjectdetectionapi.exception.ImageNotFoundException;
import org.example.imageobjectdetectionapi.exception.ImaggaBadRequestException;
import org.example.imageobjectdetectionapi.model.ImageRequest;
import org.example.imageobjectdetectionapi.entity.Image;
import org.example.imageobjectdetectionapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/images")
public class ImageObjectDetectionController {

    ImageService imageService;

    @Autowired
    public ImageObjectDetectionController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity getAllImages(@RequestParam(name = "objects", required = false) String objects) {
        try {
            List<Image> images;

            images = null != objects ? imageService.findAllWithObjects(objects) : imageService.findAll();

            return null == images || images.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(images);
        } catch (DataAccessException e) {
            log.error("An issue occurred while executing the query to retrieve images", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an issue retrieving images -- " + e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/{imageId}")
    @ResponseBody
    public ResponseEntity getImageById(@PathVariable(name = "imageId") long imageId) {
        try {
            Image image = imageService.findById(imageId);
            return ResponseEntity.ok(image);
        } catch (ImageNotFoundException e) {
            log.error("An issue occurred while executing the query to retrieve the image", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addImage(@RequestBody ImageRequest imageRequest) {
        try {
            Image image = imageService.saveImage(imageRequest);

            return ResponseEntity.ok(image);
        } catch (DataAccessException e) {
            log.error("An issue occurred while executing the query to save the image", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an issue saving the image");
        } catch (ImaggaBadRequestException e) {
            log.error("There was an issue sending the request to Imagga -- " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
