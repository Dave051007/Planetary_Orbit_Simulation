package com.mycompany.planetary_orbit_simulation.Data_Model;

import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * 
 * @author admin
 */
public final class BodyImages {
    private static final String IMAGE_FOLDER =
            "/com/mycompany/planetary_orbit_simulation/images/";

    private BodyImages() {}

    /**
     * Returns a fill for a Circle, or null if the image is missing.
     */
    public static ImagePattern load(String fileName) {
        if (fileName == null) {
            return null;
        }

        InputStream stream = BodyImages.class.getResourceAsStream(IMAGE_FOLDER + fileName);
        if (stream == null) {
            System.err.println("Image not found: " + IMAGE_FOLDER + fileName);
            return null;
        }

        Image image = new Image(stream);
        if (image.isError()) {
            System.err.println("Could not read image: " + fileName);
            return null;
        }

        return new ImagePattern(image);   // stretches over the circle's bounds
    }
}