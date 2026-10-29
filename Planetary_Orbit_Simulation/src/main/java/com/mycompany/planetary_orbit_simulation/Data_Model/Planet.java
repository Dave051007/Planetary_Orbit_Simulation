/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation.Data_Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Admin
 */
public class Planet {
    private String name;
    private Sun sun;
    private double mass;
    
    // physical position
    private double x;
    private double y;
    
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
        this.mass = mass;
        
        // Indicates initial position of planet
        this.x = x;
        this.y = y;
        
        // Indicates direction
        this.vx = 0;
        this.vy = 0;
        
        // Initial values are 0
        this.ax = 0;
        this.ay = 0;
        
        this.shape = new Circle(radius, planetColor);
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
}
