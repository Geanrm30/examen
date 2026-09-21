package ni.edu.uam.examen.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ni.edu.uam.examen.models.Estudiante;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class RegistroController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCarrera;

    @FXML
    private TextField txtCiudad;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private ImageView imgFoto;

    @FXML
    public void guardarEstudiante(ActionEvent event) {
        String codigo = (txtCodigo != null && txtCodigo.getText() != null) ? txtCodigo.getText().trim() : "";
        String nombres = (txtNombres != null && txtNombres.getText() != null) ? txtNombres.getText().trim() : "";
        String apellidos = (txtApellidos != null && txtApellidos.getText() != null) ? txtApellidos.getText().trim() : "";

        String carrera = "";
        if (txtCarrera != null && txtCarrera.getText() != null && !txtCarrera.getText().trim().isEmpty()) {
            carrera = txtCarrera.getText().trim();
        } else if (txtCiudad != null && txtCiudad.getText() != null && !txtCiudad.getText().trim().isEmpty()) {
            carrera = txtCiudad.getText().trim();
        }

        LocalDate fechaNacimiento = (dpFechaNacimiento != null) ? dpFechaNacimiento.getValue() : null;

        if (codigo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || carrera.isEmpty() || fechaNacimiento == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor complete todos los campos obligatorios.");
            return;
        }

        Estudiante estudiante = new Estudiante(codigo, nombres, apellidos, carrera, fechaNacimiento);
        ConsultaController.agregar(estudiante);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Registro Exitoso", "El estudiante ha sido registrado correctamente.");
        limpiarFormulario();
    }

    @FXML
    public void guardarCliente(ActionEvent event) {
        guardarEstudiante(event);
    }

    @FXML
    public void limpiarFormulario() {
        if (txtCodigo != null) txtCodigo.clear();
        if (txtNombres != null) txtNombres.clear();
        if (txtApellidos != null) txtApellidos.clear();
        if (txtCarrera != null) txtCarrera.clear();
        if (txtCiudad != null) txtCiudad.clear();
        if (dpFechaNacimiento != null) dpFechaNacimiento.setValue(null);
        if (imgFoto != null) imgFoto.setImage(null);
    }

    @FXML
    public void seleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Fotografía");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = null;
        if (txtCodigo != null && txtCodigo.getScene() != null) {
            stage = (Stage) txtCodigo.getScene().getWindow();
        }
        File archivo = fileChooser.showOpenDialog(stage);
        if (archivo != null && imgFoto != null) {
            Image imagen = new Image(archivo.toURI().toString());
            imgFoto.setImage(imagen);
        }
    }

    @FXML
    public void abrirConsulta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ni/edu/uam/examen/consulta-view.fxml")
            );
            Parent root = loader.load();
            Stage stage = (Stage) txtCodigo.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Lista de Estudiantes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la vista de consulta: " + e.getMessage());
        }
    }

    @FXML
    public void volverAlMenu(ActionEvent event) {
        abrirConsulta(event);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
