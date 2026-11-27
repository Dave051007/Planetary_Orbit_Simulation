package com.mycompany.planetary_orbit_simulation.Controllers;

import com.mycompany.planetary_orbit_simulation.Data_Model.SolarSystem;
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
        animationController = new AnimationController(animationAreaPane);

        // Control Bar event handlers
        zoomLbl.textProperty().bind(
                zoomSlider.valueProperty().asString("Zoom (%.0fx)"));
        
        zoomSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setZoom(newValue.doubleValue());
        });
        
        // Animation speed function
        speedLbl.textProperty().bind(speedSlider.valueProperty().
                asString("Simulation Speed (%.0fx)"));
        
        speedSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setAnimationSpeed((double) newValue);
        }); 
        
        minBtn.setOnAction(e -> {
            if (zoomSlider.getValue() > zoomSlider.getMin()) {
                zoomSlider.setValue(zoomSlider.getValue() - 1);
            }
        });
        
        plusBtn.setOnAction(e -> {
            if (zoomSlider.getValue() < zoomSlider.getMax()) {
                zoomSlider.setValue(zoomSlider.getValue() + 1);
            }
        });
        
        // Show planets checbox listener
        planetsShowCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setPlanetsVisible(newValue);
        });
        
        // Show planet name checkbox listener
        planetNamesShowCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setNamesVisible(newValue);
        });
        
        // Show trails checkbox listener
        //
        
        // Show orbit lines checkbox listener
        orbitLinesShowCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setOrbitLinesVisible(newValue);
        });
        
        // Make body visible checkbox listeners
        sunCb.selectedProperty().addListener((obs, oldValue, newValue) -> {
            animationController.setBodyVisible(
                    animationController.getSolarSystem().getSun().getShape(),
                    newValue
            );
            
            animationController.setSunGravity(newValue);
        });

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
        
        // Sync the pane with the checkboxes' initial state
        // The Sun, Mercury, Venus, Earth, and Mars are on AnimationPane by default
        SolarSystem system = animationController.getSolarSystem();
        
        animationController.setBodyVisible(system.getSun().getShape(), sunCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Mercury").getShape(), mercuryCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Venus").getShape(), venusCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Earth").getShape(), earthCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Mars").getShape(), marsCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Jupiter").getShape(), jupiterCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Saturn").getShape(), saturnCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Uranus").getShape(), uranusCb.isSelected());
        animationController.setBodyVisible(system.getPlanet("Neptune").getShape(), neptuneCb.isSelected());
        
        // Planets are visible by default as well as their orbit lines
        animationController.setPlanetsVisible(planetsShowCb.isSelected());
        animationController.setOrbitLinesVisible(orbitLinesShowCb.isSelected());
    }
    
    @FXML
    void startMotion(ActionEvent event) { 
        animationController.startAnimation();
        pauseBtn.setDisable(false);
        startBtn.setDisable(true);
        resetBtn.setDisable(false);
    }
    
    @FXML
    void pauseAnimation(ActionEvent event) {
        animationController.pauseAnimation(pauseBtn);
    }
    
    @FXML
    void resetAnimation(ActionEvent event) {
        animationController.resetAnimation();
        pauseBtn.setDisable(true);
        startBtn.setDisable(false);
        resetBtn.setDisable(true);
    }
}