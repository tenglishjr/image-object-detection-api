package org.example.imageobjectdetectionapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ImageRequest {

	@NonNull
	private String imageUrl;

	private String label;

	private boolean useObjectDetection;

	@Override
	public String toString() {
		return "ImageRequest{" + "imageUrl='" + imageUrl + '\'' + ", label='" + label + '\'' + ", useObjectDetection="
				+ useObjectDetection + '}';
	}

}
