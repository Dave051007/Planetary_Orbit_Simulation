package com.mycompany.planetary_orbit_simulation.Controllers;

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
        
        AnimationController animationController = new AnimationController(animationAreaPane);
        
        // Checkboxes "Celestial Bodies" event handlers
        sunCb.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            animationController.setSunVisible(isSelected);
        });
    }
    
}
