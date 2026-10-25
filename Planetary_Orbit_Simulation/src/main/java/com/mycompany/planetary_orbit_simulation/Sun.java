/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Admin
 */
public class Sun {
    private static final double mass = 1.989e30;
    
    private double sunX;
    private double sunY;
    
    private double radius;
    private Circle shape;

    public Sun(double sunX, double sunY, double radius) {
        this.sunX = sunX;
        this.sunY = sunY;
        
        this.shape = new Circle(radius);
        shape.setCenterX(sunX);
        shape.setCenterY(sunY);
        shape.setFill(Color.YELLOW);
    }
}
