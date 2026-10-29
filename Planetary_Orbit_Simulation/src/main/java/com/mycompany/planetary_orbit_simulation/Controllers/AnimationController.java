/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planetary_orbit_simulation.Controllers;

import com.mycompany.planetary_orbit_simulation.Data_Model.SolarSystem;
import com.mycompany.planetary_orbit_simulation.Data_Model.Sun;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 *
 * @author Admin
 */
public class AnimationController {
    
    private final Pane animationPane;
    private final SolarSystem solarSystem;
    
    public AnimationController(Pane animationPane) {
        this.animationPane = animationPane;
        this.solarSystem = new SolarSystem(new Sun(300.0, 300.0, 30.0));
        initSun();
    }
    
    private void initSun() {
        Circle sunShape = solarSystem.getSun().getShape();
        sunShape.centerXProperty().bind(animationPane.widthProperty().divide(2));
        sunShape.centerYProperty().bind(animationPane.heightProperty().divide(2));
        animationPane.getChildren().add(sunShape);
    }
    
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }
}
