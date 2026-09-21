module ni.edu.uam.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens ni.edu.uam.examen to javafx.fxml;
    opens ni.edu.uam.examen.controllers to javafx.fxml;
    opens ni.edu.uam.examen.models to javafx.base;

    exports ni.edu.uam.examen;
    exports ni.edu.uam.examen.controllers;
    exports ni.edu.uam.examen.models;
}