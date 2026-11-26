package com.mycompany.planetary_orbit_simulation.Data_Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Admin
 */
public class Planet {
    private String name;
    private Sun sun;
    private double mass;
    
    // postion
    private double x;
    private double y;
    
    private double startX;
    private double startY;
    
    // velocity
    private double vx;
    private double vy;
    
    // acceleration
    private double ax;
    private double ay;
    
    private Circle shape;
    private double radius;
    private Color planetColor;

    public Planet(String name, double mass, double x, double y, double radius, Color planetColor) {
        this(name, mass, x, y, radius, planetColor, null);
    }

    public Planet(String name, double mass, double x, double y, double radius,
                  Color planetColor, String imageFile) {
        this.name = name;
        this.mass = mass;

        // Indicates initial position of planet
        this.x = x;
        this.y = y;

        this.startX = x;
        this.startY = y;

        // Indicates direction
        this.vx = 0;
        this.vy = 0;

        // Initial values are 0
        this.ax = 0;
        this.ay = 0;

        this.radius = radius;
        this.planetColor = planetColor;
        this.shape = new Circle(radius, planetColor);
        
        ImagePattern pattern = BodyImages.load(imageFile);
        if (pattern != null) {
            this.shape.setFill(pattern);
        }
    }
    
    /**
     * Sets the planet's position, acceleration and velocity
     * to its beginning state.
     */
    public void resetPosition() {
        x = startX;
        y = startY;

        vx = 0;
        vy = 0;

        ax = 0;
        ay = 0;
    }

    public Circle getShape() {
        return shape;
    }

    public Color getColor() {
        return planetColor;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public String getName() {
        return name;
    }

    public Sun getSun() {
        return sun;
    }

    public double getMass() {
        return mass;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public double getAx() {
        return ax;
    }

    public double getAy() {
        return ay;
    }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
    public void setAcceleration(double ax, double ay) {
        this.ax = ax;
        this.ay = ay;
    }
    
    public void setSun(Sun sun) {
        this.sun = sun;
    }
}