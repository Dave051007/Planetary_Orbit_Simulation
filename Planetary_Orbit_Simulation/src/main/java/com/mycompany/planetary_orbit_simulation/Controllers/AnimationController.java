/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation.Controllers;

import com.mycompany.planetary_orbit_simulation.Data_Model.Planet;
import com.mycompany.planetary_orbit_simulation.Data_Model.SolarSystem;
import com.mycompany.planetary_orbit_simulation.Data_Model.Sun;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class AnimationController {
    private double SCALE =  1e9;           // 1 pixel = 1 * 10^9 m (temporary)
    private static final double MAX_ORBIT = 4.495e12;   // Neptune's distance (m)
    private static final double MAX_PIXELS = 280;       // how far Neptune is drawn from the Sun
    
    private double TIME_SCALE = 86400;     // 1 second = 86400 simulation seconds
    
    private final Pane animationPane;
    private final SolarSystem solarSystem;
    
    private boolean namesVisible = false;
    private final Map<Planet, Text> nameTexts = new HashMap<>();
    
    public AnimationController(Pane animationPane) {
        this.animationPane = animationPane;
        this.solarSystem = new SolarSystem(new Sun(300.0, 300.0, 18.0));
        
        animationPane.widthProperty().addListener((obs, oldValue, newValue) -> {
            updateAllPlanetPositions();
        });

        animationPane.heightProperty().addListener((obs, oldValue, newValue) -> {
            updateAllPlanetPositions();
        });
        
        initSun();
        initOrbits();
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

        // Real distance from the Sun (meters)
        double realDistance = Math.hypot(planet.getX(), planet.getY());

        // Space available from the Sun to the edge of the pane (pixels)
        double maxScreenDistance = Math.max(
                Math.min(animationPane.getWidth(), animationPane.getHeight()) / 2 - 20, 0);

        // Distance on screen (pixels), compressed so Neptune lands at the edge
        double screenDistance = Math.sqrt(realDistance / MAX_ORBIT) * maxScreenDistance;

        // Keep the planet's real direction, but place it at the screen distance
        double screenX = sunX + planet.getX() / realDistance * screenDistance;
        double screenY = sunY + planet.getY() / realDistance * screenDistance;

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
    * Creates an orbit for every planet and adds it to the animation pane
    */
    private void initOrbits() {
        Circle sunShape = solarSystem.getSun().getShape();

        for (Planet planet : solarSystem.getPlanets()) {
            double r = Math.hypot(planet.getX(), planet.getY());

            Circle orbit = new Circle();
            orbit.setFill(Color.TRANSPARENT);
            orbit.setStroke(Color.GRAY);
            orbit.setMouseTransparent(true);
            orbit.setManaged(false);                       // never stretches the layout
            orbit.centerXProperty().bind(sunShape.centerXProperty());
            orbit.centerYProperty().bind(sunShape.centerYProperty());
            orbit.radiusProperty().bind(Bindings.createDoubleBinding(
                    () -> Math.sqrt(r / MAX_ORBIT)
                            * (Math.min(animationPane.getWidth(), animationPane.getHeight()) / 2 - 20),
                    animationPane.widthProperty(), animationPane.heightProperty()));
            orbit.visibleProperty().bind(planet.getShape().parentProperty().isNotNull());  // hidden planet = hidden line

            animationPane.getChildren().add(orbit);
        }
    }
    
    /**
     * Creates the planets and adds it to the animation pane
     */
    private void initPlanets() {
        for (Planet planet : solarSystem.getPlanets()) {
            Circle planetShape = planet.getShape();
            planetShape.setManaged(false);
            updatePlanetPosition(planet);
            animationPane.getChildren().add(planetShape);
            Text nameText = new Text(planet.getName());
            nameText.setMouseTransparent(true);
            nameText.setManaged(false);
            nameText.setVisible(false);
            nameText.xProperty().bind(planetShape.centerXProperty().add(planetShape.radiusProperty()).add(4));
            nameText.yProperty().bind(planetShape.centerYProperty().subtract(planetShape.radiusProperty()).subtract(2));
            nameTexts.put(planet, nameText);
            animationPane.getChildren().add(nameText);
        }
    }
    
    public void setNamesVisible(boolean visible) {
    namesVisible = visible;
    for (Map.Entry<Planet, Text> entry : nameTexts.entrySet()) {
        // a name shows only if names are on AND its planet is in the pane
        entry.getValue().setVisible(
                namesVisible && animationPane.getChildren().contains(entry.getKey().getShape()));
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
        
        setNamesVisible(namesVisible);
    }
    
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }
}
