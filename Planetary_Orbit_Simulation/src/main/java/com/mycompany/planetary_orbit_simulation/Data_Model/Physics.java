/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation.Data_Model;

/**
 *
 * @author Dave
 */
public class Physics {
    private final double GRAVITATIONAL_CONSTANT = 6.6743e-11;
    
    /**
     * Calculates the orbital acceleration of a planet based on the planet's parameters
     * @param planet the planet with its parameters
     */
    public void calculateAcceleration(Planet planet) {
        // Initial vector postion from the planet towards the sun
        double x = -planet.getX();
        double y = -planet.getY();
        
        double distance = Math.sqrt((x * x)+(y * y));
        
        double force = GRAVITATIONAL_CONSTANT * planet.getSun().getMass() * planet.getMass() / (distance * distance);
        
        double a = force / planet.getMass();
        
        double angle = Math.atan2(y, x);
        
        double ax = a * Math.cos(angle);
        double ay = a * Math.sin(angle);
        
        planet.setAcceleration(ax, ay);
    }
        
   /**
    * Calculates the instantaneous velocity of the planet.
    * @param planet the planet that has the velocity to be updated
    * @param dt the very small interval of time
    */
    public void calculateVelocity(Planet planet, double dt) {
        double oldAx = planet.getAx();
        double oldAy = planet.getAy();
        
        double oldVx = planet.getVx();
        double oldVy = planet.getVy();
        
        calculateAcceleration(planet);
        
        double newAx = planet.getAx();
        double newAy = planet.getAy();
        
        double newVx = oldVx + ((oldAx + newAx) * 0.5) * dt;
        double newVy = oldVy + ((oldAy + newAy) * 0.5) * dt;
        
        planet.setAcceleration(newVx, newVy);
    }
    
    /**
     * Calculates the planet's initial velocity when planet is added
     * @param planet the planet with initial velocity when added to the animationPane
     * @param orbitalRadius the physical distance of the planet from the sun
     */
    public void calculateInitialVelocity(Planet planet, double orbitalRadius) {
        double vy = Math.sqrt(GRAVITATIONAL_CONSTANT * planet.getMass() / orbitalRadius);
        
        planet.setVelocity(0, vy);
    }
    
    /**
     * Calculates the planet's new position in a small interval of time
     * @param planet the planet that has the position to be updated
     * @param dt the small interval of time
     */
    public void calculatePosition(Planet planet, double dt) {       // dt - small time interval
        double vx = planet.getVx();
        double vy = planet.getVy();
        
        double ax = planet.getAx();
        double ay = planet.getAy();
        
        double dx = vx * dt + 0.5 * ax * (dt * dt);
        double dy = vy * dt + 0.5 * ay * (dt * dt);
        
        double newX = planet.getX() + dx;
        double newY = planet.getY() + dy;
        
        planet.setPosition(newX, newY);
    }
}
