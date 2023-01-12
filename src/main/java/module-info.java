module com.example.dontopen {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dontopen to javafx.fxml;
    exports com.example.dontopen;
    exports com.example.dontopen.view;
}