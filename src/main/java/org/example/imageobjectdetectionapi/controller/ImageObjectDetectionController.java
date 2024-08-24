package org.example.imageobjectdetectionapi.controller;

import org.example.imageobjectdetectionapi.model.Image;
import org.example.imageobjectdetectionapi.model.ImageRequest;
import org.example.imageobjectdetectionapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageObjectDetectionController {

    ImageService imageService;

    @Autowired
    public ImageObjectDetectionController(ImageService imageService) {
        this.imageService = imageService;
    }

    // ---- get all image data
    @GetMapping(path = "/images")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.findAll();
        return images == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(images, HttpStatus.OK);
    }

    // ---- get one image data
    @GetMapping(path = "/images/{imageId}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Image> getImageById(@PathVariable Long imageId) {
        Image image = imageService.findById(imageId);
        return image == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(image, HttpStatus.OK);
    }

    // ---- Get images containing x,y objects


    // ---- call Imagga for image object detection, save the image, then return image w/ metadata
    @PostMapping(path = "/images")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<Image> addImage(@RequestBody ImageRequest imageRO) {
        // send image URL to Imagga & get back metadata

        // map ImaggaRO to Image object

        // save away to DB

        // return new Image
        return new ResponseEntity<>(new Image(), HttpStatus.CREATED);
    }
}
