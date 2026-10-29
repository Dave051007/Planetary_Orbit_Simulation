module com.mycompany.planetary_orbit_simulation {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.mycompany.planetary_orbit_simulation;
    opens com.mycompany.planetary_orbit_simulation to javafx.fxml;

    opens com.mycompany.planetary_orbit_simulation.Controllers to javafx.fxml;
}
