package mathdiffer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mathdiffer.Calculation;
import mathdiffer.MathDiffer;

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

    @FXML
    private Button manual_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void show_result(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ответ");
        alert.setHeaderText(field_form.getText());
        alert.setContentText(Calculation.get(Double.valueOf(field_a.getText()), Double.valueOf(field_b.getText()), field_form.getText()));
        alert.show();
    }

    @FXML
    private void show_settings(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MathDiffer.class.getResource("fxml/MainWin.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setResizable(false);

            SettingsWinController controller = loader.getController();
            // do something 

            stage.setScene(scene);
            stage.show();
        } catch (IOException iOException) {
        }
    }

    @FXML
    private void show_manual(ActionEvent event) {
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
