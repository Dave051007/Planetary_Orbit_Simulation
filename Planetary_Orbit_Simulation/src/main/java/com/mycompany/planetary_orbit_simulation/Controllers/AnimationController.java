/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation.Controllers;

import com.mycompany.planetary_orbit_simulation.Data_Model.Planet;
import com.mycompany.planetary_orbit_simulation.Data_Model.SolarSystem;
import com.mycompany.planetary_orbit_simulation.Data_Model.Sun;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Admin
 */
public class AnimationController {
    private double SCALE =  1e9;           // 1 pixel = 1 * 10^9 m (temporary)
    private double TIME_SCALE = 86400;     // 1 second = 86400 simulation seconds
    
    private final Pane animationPane;
    private final SolarSystem solarSystem;
    
    public AnimationController(Pane animationPane) {
        this.animationPane = animationPane;
        this.solarSystem = new SolarSystem(new Sun(300.0, 300.0, 25.0));
        
        animationPane.widthProperty().addListener((obs, oldValue, newValue) -> {
            updateAllPlanetPositions();
        });

        animationPane.heightProperty().addListener((obs, oldValue, newValue) -> {
            updateAllPlanetPositions();
        });
        
        initSun();
        initPlanets();
    }
    /**
     * Converts the planet's position into its JavaFX position. 
     * (real life position -> position in animation pane)
     * @param planet the planet of interest
     */
    private void updatePlanetPosition(Planet planet) {
        Circle sunShape = solarSystem.getSun().getShape();
        Circle planetShape = planet.getShape();
        
        double sunX = sunShape.getCenterX();
        double sunY = sunShape.getCenterY();
        
        double screenX = sunX + planet.getX() / SCALE;
        double screenY = sunY + planet.getY() / SCALE;
        
        planetShape.setCenterX(screenX);
        planetShape.setCenterY(screenY);
    }
    
     private void updateAllPlanetPositions() {
        for (Planet planet : solarSystem.getPlanets()) {
            updatePlanetPosition(planet);
        }
    }
    
    /**
     * Creates a sun and adds it to the animation pane
     */
    private void initSun() {
        Circle sunShape = solarSystem.getSun().getShape();
        
        sunShape.centerXProperty().bind(animationPane.widthProperty().divide(2));
        sunShape.centerYProperty().bind(animationPane.heightProperty().divide(2));
        
        animationPane.getChildren().add(sunShape);
    }
    
    /**
     * Creates the planets and adds it to the animation pane
     */
    private void initPlanets() {
        for (Planet planet : solarSystem.getPlanets()) {
            Circle planetShape = planet.getShape();
            updatePlanetPosition(planet);
            animationPane.getChildren().add(planetShape);
        }
    }
    
    /**
     * Makes a body of the solar system(e.g. Sun, planets) visible in the 
     * animation pane
     * @param bodyShape the shape of the body
     * @param visible true: body is visible, false: not visible
     */
    public void setBodyVisible(Circle bodyShape, boolean visible) {
        if (visible) {
            if (!animationPane.getChildren().contains(bodyShape)) {
                animationPane.getChildren().add(bodyShape);
            }
        } else {
            animationPane.getChildren().remove(bodyShape);
        }
    }
    
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }
}