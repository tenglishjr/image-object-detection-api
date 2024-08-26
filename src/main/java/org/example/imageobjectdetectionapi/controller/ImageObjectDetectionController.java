package org.example.imageobjectdetectionapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.imageobjectdetectionapi.model.Image;
import org.example.imageobjectdetectionapi.dto.ImageRequest;
import org.example.imageobjectdetectionapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class ImageObjectDetectionController {

	ImageService imageService;

	@Autowired
	public ImageObjectDetectionController(ImageService imageService) {
		this.imageService = imageService;
	}

	// ---- get all image data
	@GetMapping(path = "/images")
	@ResponseBody
	public ResponseEntity<List<Image>> getAllImages(@RequestParam(name = "objects", required = false) String objects) {
		List<Image> images;

		if (objects != null) {
			String[] tags = objects.replace("\"", "").split(",");
			images = imageService.findAllWithObjects(tags);
			log.error("tags: " + Arrays.deepToString(tags));
		}
		else {
			images = imageService.findAll();
		}

		return images == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(images, HttpStatus.OK);
	}

	@GetMapping(path = "/images/{imageId}")
	@ResponseBody
	public ResponseEntity<Image> getImageById(@PathVariable(name = "imageId") long imageId) {
		Image image = imageService.findById(imageId);
		return image == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(image, HttpStatus.OK);
	}

	@PostMapping(path = "/images")
	@ResponseBody
	public ResponseEntity<Image> addImage(@RequestBody ImageRequest imageRequest) {
		log.error(imageRequest.toString());
		try {
			Image image = imageService.saveImage(imageRequest);

			return new ResponseEntity<>(image, HttpStatus.CREATED);
		}
		catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
