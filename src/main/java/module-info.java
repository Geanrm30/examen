module ni.edu.uam.examen {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.examen to javafx.fxml;
    exports ni.edu.uam.examen;
}