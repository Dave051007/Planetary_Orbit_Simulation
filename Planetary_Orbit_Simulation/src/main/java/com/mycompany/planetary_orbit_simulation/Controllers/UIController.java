package com.mycompany.planetary_orbit_simulation.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Leo
 */
public class UIController {
    
    @FXML private Pane animationAreaPane;
    
    // Celestial Bodies
    @FXML private CheckBox sunCb;    
    @FXML private CheckBox mercuryCb;
    @FXML private CheckBox venusCb;
    @FXML private CheckBox earthCb;
    @FXML private CheckBox marsCb;
    @FXML private CheckBox jupiterCb;
    @FXML private CheckBox saturnCb;
    @FXML private CheckBox uranusCb;
    @FXML private CheckBox neptuneCb;

    // Planet Parameters
    @FXML private Label planetNameLbl;
    @FXML private Label massLbl;
    @FXML private Label positionLbl;
    @FXML private Label velocityLbl;
    @FXML private Label accelerationLbl;
    @FXML private Label distanceSunLbl;    
    
     // Simulation Settings
    @FXML private Label speedLbl;
    @FXML private Slider speedSlider;
    @FXML private CheckBox planetsShowCb;
    @FXML private CheckBox planetNamesShowCb;
    @FXML private CheckBox trailsShowCb;
    @FXML private CheckBox orbitLinesShowCb;
    
     // Control bar
    @FXML private Label zoomLbl;
    @FXML private Slider zoomSlider;
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button resetBtn;
    @FXML private Button minBtn;
    @FXML private Button plusBtn;
    
    private AnimationController animationController;

    public void initialize() {
        
        // Control Bar event handlers
        zoomLbl.textProperty().bind(
                zoomSlider.valueProperty().asString("Zoom (%.0fx)"));
        
        speedLbl.textProperty().bind(speedSlider.valueProperty().
                asString("Simulation Speed (%.0fx)"));
        
        minBtn.setOnAction(e -> {
            if (zoomSlider.getValue() != 1) {
                zoomSlider.setValue(zoomSlider.getValue() - 1);
            }
        });
        
        plusBtn.setOnAction(e -> {
            if (zoomSlider.getValue() != 100) {
                zoomSlider.setValue(zoomSlider.getValue() + 1);
            }
        });
        
        animationController = new AnimationController(animationAreaPane);
        
        planetNamesShowCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setNamesVisible(newValue);
        });
        
        planetNamesShowCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setNamesVisible(newValue);
        });
        
        animationController.setBodyVisible(
        animationController.getSolarSystem().getPlanet("Jupiter").getShape(), false);
        animationController.setBodyVisible(
                animationController.getSolarSystem().getPlanet("Saturn").getShape(), false);
        animationController.setBodyVisible(
                animationController.getSolarSystem().getPlanet("Uranus").getShape(), false);
        animationController.setBodyVisible(
                animationController.getSolarSystem().getPlanet("Neptune").getShape(), false);
        
        // Sun
        sunCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getSun().getShape(),
                    newValue
            );
        });

        // Planets
        mercuryCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Mercury").getShape(),
                    newValue
            );
        });

        venusCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Venus").getShape(),
                    newValue
            );
        });

        earthCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Earth").getShape(),
                    newValue
            );
        });
        
        marsCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Mars").getShape(),
                    newValue
            );
        });
        
        jupiterCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Jupiter").getShape(),
                    newValue
            );
        });
        
        saturnCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Saturn").getShape(),
                    newValue
            );
        });
        
        uranusCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Uranus").getShape(),
                    newValue
            );
        });
        
        neptuneCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getPlanet("Neptune").getShape(),
                    newValue
            );
        });
    }
    
    @FXML
    void startMotion(ActionEvent event) { 
        animationController.startAnimation();
    }
}