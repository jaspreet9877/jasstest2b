module com.example.test2b_jaspreet {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.test2b_jaspreet to javafx.fxml;
    exports com.example.test2b_jaspreet;
}