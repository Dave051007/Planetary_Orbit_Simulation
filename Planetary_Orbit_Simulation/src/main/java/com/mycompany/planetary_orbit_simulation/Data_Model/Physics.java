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
     */
    public void calculateAccelerations(SolarSystem solarSystem) {
        Sun sun = solarSystem.getSun();

        for (Planet planet : solarSystem.getPlanets()) {
            double dx = sun.getSunX() - planet.getX();
            double dy = sun.getSunY() - planet.getY();

            double distanceSquared = dx * dx + dy * dy;

            if (distanceSquared == 0) {
                planet.setAcceleration(0, 0);
                continue;
            }

            double distance = Math.sqrt(distanceSquared);

            // Calculate gravitational acceleration
            double acceleration = GRAVITATIONAL_CONSTANT
                    * sun.getMass() / (distance * distance);

            // Calculate angle toward the Sun
            double angle = Math.atan2(dy, dx);

            // Calculate X and Y acceleration
            double ax = acceleration * Math.cos(angle);
            double ay = acceleration * Math.sin(angle);

            planet.setAcceleration(ax, ay);
        }
    }

    /**
     * Calculates the planet's initial velocity when planet is added
     * @param planet the planet with initial velocity when added to the animationPane
     * @param orbitalRadius the physical distance of the planet from the sun
     */
    public void calculateInitialVelocity(Planet planet, double orbitalRadius) {
        double dx = planet.getX() - planet.getSun().getSunX();
        double dy = planet.getY() - planet.getSun().getSunY();

        double distance = Math.hypot(dx, dy);

        if (distance == 0 || orbitalRadius <= 0) {
            return;
        }

        double circularSpeed = Math.sqrt(
                GRAVITATIONAL_CONSTANT
                * planet.getSun().getMass()
                / distance
        );

        // 95% of circular speed
        double speed = circularSpeed * 0.95;
        double angle = Math.atan2(dy, dx);

        // Calculate velocity using trigonometry
        double vx = speed * Math.cos(angle + Math.PI / 2);
        double vy = speed * Math.sin(angle + Math.PI / 2);

        planet.setVelocity(vx, vy);
    }

    /**
     * Updates the positions and velocities of all planets
     */
    public void updatePhysics(SolarSystem solarSystem, double dt) {
        if (dt <= 0) {
            return;
        }

        int numberOfPlanets = solarSystem.getPlanets().size();

        // Calculate initial accelerations
        calculateAccelerations(solarSystem);

        // Save initial accelerations
        double[][] oldAccelerations = new double[numberOfPlanets][2];

        for (int i = 0; i < numberOfPlanets; i++) {
            Planet planet = solarSystem.getPlanets().get(i);

            oldAccelerations[i][0] = planet.getAx();
            oldAccelerations[i][1] = planet.getAy();
        }

        // Update all planet positions
        for (Planet planet : solarSystem.getPlanets()) {

            double newX = planet.getX()
                    + planet.getVx() * dt
                    + 0.5 * planet.getAx() * dt * dt;

            double newY = planet.getY()
                    + planet.getVy() * dt
                    + 0.5 * planet.getAy() * dt * dt;

            planet.setPosition(newX, newY);
        }

        // Calculate new accelerations using new positions
        calculateAccelerations(solarSystem);

        // Update all planet velocities
        for (int i = 0; i < numberOfPlanets; i++) {

            Planet planet = solarSystem.getPlanets().get(i);

            double newVx = planet.getVx()
                    + 0.5 * (oldAccelerations[i][0]
                    + planet.getAx()) * dt;

            double newVy = planet.getVy()
                    + 0.5 * (oldAccelerations[i][1]
                    + planet.getAy()) * dt;

            planet.setVelocity(newVx, newVy);
        }
    }
}