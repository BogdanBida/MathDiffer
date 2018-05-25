package mathdiffer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mathdiffer.Calculation;

public class MainWinController implements Initializable {

    @FXML
    private TextField field_a;

    @FXML
    private TextField field_b;

    @FXML
    private TextField field_form;

    @FXML
    private Button btn_enter;

    @FXML
    private Button btn_settings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void show_result() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ответ");
        alert.setHeaderText(field_form.getText());
        String a = field_a.getText();
        String b = field_b.getText();
        a = String.valueOf(Calculation.f(0, a));
        b = String.valueOf(Calculation.f(0, b));
        alert.setContentText(Calculation.get(Double.valueOf(a), Double.valueOf(b), field_form.getText()));
        alert.show();
    }

    @FXML
    public void show_manual() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ManualWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Справка");
            stage.setResizable(true);
            stage.initModality(Modality.NONE);
            stage.setScene(scene);
            stage.show();
        } catch (IOException iOException) {
        }
    }
}
