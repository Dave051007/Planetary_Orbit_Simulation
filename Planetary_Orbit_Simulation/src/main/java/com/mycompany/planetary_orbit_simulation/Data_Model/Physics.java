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
        double x = -planet.getX();
        double y = -planet.getY();

        double distance = Math.hypot(x, y);

        if (distance == 0) {
            planet.setAcceleration(0, 0);
            return;
        }

        double acceleration = GRAVITATIONAL_CONSTANT
                * planet.getSun().getMass()
                / (distance * distance);

        double ax = acceleration * (x / distance);
        double ay = acceleration * (y / distance);

        planet.setAcceleration(ax, ay);
    }
        
   /**
    * Calculates the instantaneous velocity of the planet.
    * @param planet the planet that has the velocity to be updated
    * @param dt the very small interval of time
    */
    public void updateVelocity(Planet planet, double dt) {
        // Previous acceleration
        double oldAx = planet.getAx();
        double oldAy = planet.getAy();

        // Previous velocity
        double oldVx = planet.getVx();
        double oldVy = planet.getVy();

        // Calculate acceleration at the new position
        calculateAcceleration(planet);

        // New acceleration
        double newAx = planet.getAx();
        double newAy = planet.getAy();

        // Average acceleration
        double averageAx = (oldAx + newAx) / 2;
        double averageAy = (oldAy + newAy) / 2;

        // Update velocity
        double newVx = oldVx + averageAx * dt;
        double newVy = oldVy + averageAy * dt;

        planet.setVelocity(newVx, newVy);
    }
    
    /**
     * Calculates the planet's initial velocity when planet is added
     * @param planet the planet with initial velocity when added to the animationPane
     * @param orbitalRadius the physical distance of the planet from the sun
     */
    public void calculateInitialVelocity(Planet planet, double orbitalRadius) {
        double x = planet.getX();
        double y = planet.getY();

        double distance = Math.hypot(x, y);

        if (distance == 0 || orbitalRadius <= 0) {
            return;
        }

        double speed = Math.sqrt(
                GRAVITATIONAL_CONSTANT
                * planet.getSun().getMass()
                / orbitalRadius
        );

        double vx = -y / distance * speed;
        double vy = x / distance * speed;

        planet.setVelocity(vx, vy);
    }
    
    /**
     * Calculates the planet's new position in a small interval of time
     * @param planet the planet that has the position to be updated
     * @param dt the small interval of time
     */
    public void updatePosition(Planet planet, double dt) {
        double vx = planet.getVx();
        double vy = planet.getVy();

        double ax = planet.getAx();
        double ay = planet.getAy();

        double dx = vx * dt + 0.5 * ax * dt * dt;
        double dy = vy * dt + 0.5 * ay * dt * dt;

        double newX = planet.getX() + dx;
        double newY = planet.getY() + dy;

        planet.setPosition(newX, newY);
    }
    
    // Method that updates a planet's parameters over time
    public void updatePhysics(Planet planet, double dt) {
        if (dt <= 0) {
            return;
        }

        calculateAcceleration(planet);
        updatePosition(planet, dt);
        updateVelocity(planet, dt);
    }
}