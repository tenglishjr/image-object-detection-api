package org.example.imageobjectdetectionapi.controller;

import org.example.imageobjectdetectionapi.model.Image;
import org.example.imageobjectdetectionapi.service.ImageObjectDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ImageObjectDetectionController {

    ImageObjectDetectionService imageObjectDetectionService;

    @Autowired
    public ImageObjectDetectionController(ImageObjectDetectionService imageObjectDetectionService) {
        this.imageObjectDetectionService = imageObjectDetectionService;
    }

    // get all image data
    @GetMapping(path = "/images")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageObjectDetectionService.findAll();
        return images == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(images, HttpStatus.OK);
    }

    // Get images containing x,y objects

    // get one image data

    // save an image and return its data
}
