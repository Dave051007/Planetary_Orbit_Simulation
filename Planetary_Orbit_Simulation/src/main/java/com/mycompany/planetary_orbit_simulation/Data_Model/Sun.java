package com.mycompany.planetary_orbit_simulation.Data_Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Admin
 */
public class Sun {
    private final double mass = 1.989e30;
    
    private double sunX;
    private double sunY;
    
    private double radius;
    private Circle shape;

    public Sun(double sunX, double sunY, double radius) {
        this(sunX, sunY, radius, null);
    }

    public Sun(double sunX, double sunY, double radius, String imageFile) {
        this.sunX = sunX;
        this.sunY = sunY;
        
        this.shape = new Circle(radius);
        shape.setCenterX(sunX);
        shape.setCenterY(sunY);
        shape.setFill(Color.YELLOW);
        
        ImagePattern pattern = BodyImages.load(imageFile);
        if (pattern != null) {
            shape.setFill(pattern);
        }
    }

    public double getMass() {
        return mass;
    }

    public double getSunX() {
        return sunX;
    }

    public double getSunY() {
        return sunY;
    }
    
    public Circle getShape() {
        return this.shape;
    }
}
