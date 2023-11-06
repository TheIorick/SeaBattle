module com.example.task2_seabattle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task2_seabattle to javafx.fxml;
    exports com.example.task2_seabattle;
}