package org.example.imageobjectdetectionapi.util;

import org.example.imageobjectdetectionapi.dto.ImaggaRO;
import org.example.imageobjectdetectionapi.dto.TagItem;
import org.example.imageobjectdetectionapi.model.Image;
import org.example.imageobjectdetectionapi.dto.ImageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ImageMapper {

	public static Image mapToImage(ImaggaRO imaggaRO, ImageRequest imageRequest) {
		Image image = new Image();

		if (null != imaggaRO) {
			String tags = tagsAsString(imaggaRO.getResult().getTags());
			image.setTags(tags);
		}

		String label = null == imageRequest.getLabel() ? ImageUtils.labelGenerator() : imageRequest.getLabel();
		image.setLabel(label);
		image.setDateUploaded(LocalDateTime.now());

		return image;
	}

	private static String tagsAsString(List<TagItem> tagItems) {
		List<String> tags = new ArrayList<>();
		for (TagItem tagItem : tagItems) {
			tags.add(tagItem.getTag().toString());
		}
		return String.join(",", tags);
	}

}
