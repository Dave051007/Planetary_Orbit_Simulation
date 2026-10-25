/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation;

import java.util.ArrayList;

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
    }

}
