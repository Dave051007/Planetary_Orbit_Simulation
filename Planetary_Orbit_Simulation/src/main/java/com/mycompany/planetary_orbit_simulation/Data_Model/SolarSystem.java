/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation.Data_Model;

import com.mycompany.planetary_orbit_simulation.Data_Model.Planet;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Admin
 */
public class SolarSystem {
    private Sun sun;
    private ArrayList<Planet> planets;

    public SolarSystem(Sun sun) {
        this.sun = sun;
        this.planets = new ArrayList<>();
        
        // Mercury
        planets.add(new Planet("Mercury", 3.3011e23,
                5.79e10, 0, 3, Color.GRAY, "mercury.png"));

        // Venus
        planets.add(new Planet("Venus", 4.8675e24,
                1.082e11, 0, 5, Color.ORANGE, "venus.png"));

        // Earth
        planets.add(new Planet("Earth", 5.972e24,
                1.496e11, 0, 5, Color.BLUE, "earth.png"));

        // Mars
        planets.add(new Planet("Mars", 6.4171e23,
                2.279e11, 0, 4, Color.RED, "mars.png"));

        // Jupiter
        planets.add(new Planet("Jupiter", 1.8982e27,
                7.785e11, 0, 12, Color.SIENNA, "jupiter.png"));

        // Saturn
        planets.add(new Planet("Saturn", 5.6834e26,
                1.434e12, 0, 10, Color.PERU, "saturn.png"));

        // Uranus
        planets.add(new Planet("Uranus", 8.6810e25,
                2.871e12, 0, 8, Color.LIGHTBLUE, "uranus.png"));

        // Neptune
        planets.add(new Planet("Neptune", 1.02413e26,
                4.495e12, 0, 8, Color.DARKBLUE, "neptune.png"));
        
        for (Planet planet : planets) {
            planet.setSun(sun);
        }
    }

    public Sun getSun() {
        return sun;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }
    
    public Planet getPlanet(String name) {
        for (Planet planet : planets) {
            if (planet.getName().equalsIgnoreCase(name)) {
                return planet;
            }
        }

        return null;
    }
    
    public void addPlanet(Planet planet) {
        planet.setSun(sun);
        planets.add(planet);
    }
    
    public void removePlanet(Planet planet) {
        planets.remove(planet);
    }   
}