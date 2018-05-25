package mathdiffer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
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
    private Label lb_valueEps;

    @FXML
    private Label lb_valueN;

    @FXML
    private TextField tx_valueN;

    @FXML
    private Slider sl_eps;

    @FXML
    private Slider sl_N;

    BooleanProperty property = new SimpleBooleanProperty(true);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sl_eps.valueProperty().addListener((observable, oldValue, newValue) -> {
            double tempNewValue = Math.round(newValue.doubleValue());
            sl_eps.setValue(tempNewValue);
            lb_valueEps.setText(String.valueOf((int) tempNewValue));
        });
        sl_N.valueProperty().addListener((observable, oldValue, newValue) -> {
            double tempNewValue = Math.round(newValue.doubleValue());
            sl_N.setValue(tempNewValue);
            lb_valueN.setText(String.valueOf((int) tempNewValue));
        });

        tx_valueN.visibleProperty().bind(property.not());
        lb_valueN.visibleProperty().bind(property);

        tx_valueN.managedProperty().bind(property.not());
        lb_valueN.managedProperty().bind(property);

        tx_valueN.textProperty().bindBidirectional(lb_valueN.textProperty());

        lb_valueN.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                property.set(false);
            }
        });

        tx_valueN.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                property.set(true);
                sl_N.setValue(Double.valueOf(tx_valueN.getText()));
            }
        });

        lb_valueEps.setText(String.valueOf((int) sl_eps.getValue()));
        lb_valueN.setText(String.valueOf((int) sl_N.getValue()));

    }

    @FXML
    private void show_result(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ответ");
        alert.setHeaderText(field_form.getText());
        String a = field_a.getText();
        String b = field_b.getText();
        a = String.valueOf(Calculation.f(0, a));
        b = String.valueOf(Calculation.f(0, b));
        alert.setContentText(Calculation.get(Double.valueOf(a), Double.valueOf(b), field_form.getText()));
    }

    @FXML
    private void show_manual(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(MathDiffer.class.getResource("fxml/ManualWindow.fxml"));
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
    
    @FXML
    private void show_about(ActionEvent event) {
    
    }

}
