/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation;

/**
 *
 * @author Dave
 */
public class Physics {
    private final double GRAVITATIONAL_CONSTANT = 6.6743e-11;
    
    public void calculateAcceleration(Planet planet) {
        double x = planet.getX();
        double y = planet.getY();
        
        double distance = Math.sqrt((x * x)+(y * y));
        
        double force = GRAVITATIONAL_CONSTANT * planet.getSun().getMass() * planet.getMass() / (distance * distance);
        
        double a = force / planet.getMass();
        
        double angle = Math.atan2(y, x);
        
        double ax = a * Math.cos(angle);
        double ay = a * Math.cos(angle);
        
        planet.setAcceleration(ax, ay);
    }
        
    public void calculateVelocity() {
        
    }
    
    public void calculatePosition() {
        
    }
}
