package org.example.imageobjectdetectionapi.api.controller;

import org.example.imageobjectdetectionapi.api.model.Image;
import org.hibernate.annotations.NamedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ImageObjectDetectionController {

    // get all image data
    @GetMapping(path = "/images")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<String> getAllImages() {
        return Arrays.asList(new Image().toString(), new Image().toString());
    }

    // Get images containing x,y objects

    // get one image data

    // save an image and return its data
}
