package org.example.imageobjectdetectionapi.util;

import org.example.imageobjectdetectionapi.constants.LabelConstants;

import java.util.Random;

public class ImageUtils {

    public static String labelGenerator() {
        Random rn = new Random();
        int preIndex = rn.nextInt(20);
        int suffIndex = rn.nextInt(20);
        String prefix = LabelConstants.PREFIXES[preIndex];
        String suffix = LabelConstants.SUFFIXES[suffIndex];

        return prefix + " " + suffix + " (Label generated)";
    }

}
