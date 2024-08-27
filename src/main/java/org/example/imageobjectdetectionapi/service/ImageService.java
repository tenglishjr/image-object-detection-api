package org.example.imageobjectdetectionapi.service;

import org.example.imageobjectdetectionapi.model.ImageRequest;
import org.example.imageobjectdetectionapi.model.ImaggaWebResponse;
import org.example.imageobjectdetectionapi.entity.Image;
import org.example.imageobjectdetectionapi.repository.ImagesRepository;
import org.example.imageobjectdetectionapi.util.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
		return imagesRepository.findById(id).orElse(null);
	}

	public List<Image> findAllWithObjects(String[] objects) {
		return imagesRepository.findAllByTags(objects);
	}

	public Image saveImage(ImageRequest imageRequest) {
		ImaggaWebResponse imaggaWebResponse = null;
		if (imageRequest.isUseObjectDetection()) {
			imaggaWebResponse = imaggaService.getObjectDetection(imageRequest);
		}

		Image image = ImageMapper.mapToImage(imaggaWebResponse, imageRequest);
		return imagesRepository.save(image);
	}

}
