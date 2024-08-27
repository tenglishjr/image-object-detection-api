package org.example.imageobjectdetectionapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.imageobjectdetectionapi.model.ImageRequest;
import org.example.imageobjectdetectionapi.entity.Image;
import org.example.imageobjectdetectionapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<List<Image>> getAllImages(@RequestParam(name = "objects", required = false) String objects) {
		List<Image> images;

		if (objects != null) {
			String[] tags = objects.toLowerCase().replace("\"", "").split(",");
			images = imageService.findAllWithObjects(tags);
		}
		else {
			images = imageService.findAll();
		}

		return images == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(images, HttpStatus.OK);
	}

	@GetMapping(path = "/{imageId}")
	@ResponseBody
	public ResponseEntity<Image> getImageById(@PathVariable(name = "imageId") long imageId) {
		Image image = imageService.findById(imageId);
		return image == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(image, HttpStatus.OK);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Image> addImage(@RequestBody ImageRequest imageRequest) {
		log.error(imageRequest.toString());
		try {
			Image image = imageService.saveImage(imageRequest);

			return new ResponseEntity<>(image, HttpStatus.OK);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
