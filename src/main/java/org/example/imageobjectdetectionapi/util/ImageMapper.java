package org.example.imageobjectdetectionapi.util;

import org.example.imageobjectdetectionapi.model.ImageRequest;
import org.example.imageobjectdetectionapi.model.ImaggaWebResponse;
import org.example.imageobjectdetectionapi.model.TagItem;
import org.example.imageobjectdetectionapi.entity.Image;
import org.example.imageobjectdetectionapi.entity.ImageTag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ImageMapper {

    public static Image mapToImage(ImaggaWebResponse imaggaWebResponse, ImageRequest imageRequest) {
        Image image = new Image();

        if (null != imaggaWebResponse) {
            List<ImageTag> tags = mapToImageTag(imaggaWebResponse.getResult().getTags());
            image.setImageTags(tags);
        }

        String label = null == imageRequest.getLabel() ? ImageUtils.labelGenerator() : imageRequest.getLabel();
        image.setLabel(label);
        image.setDateUploaded(LocalDateTime.now());
        image.setImageUrl(imageRequest.getImageUrl());

        return image;
    }

    private static List<ImageTag> mapToImageTag(List<TagItem> tagItems) {
        List<ImageTag> imageTags = new ArrayList<>();
        for (TagItem tagItem : tagItems) {
            ImageTag imageTag = new ImageTag();
            imageTag.setTag(tagItem.getTag().toString());
            imageTag.setConfidence(tagItem.getConfidence());
            imageTags.add(imageTag);
        }
        return imageTags;
    }
}
