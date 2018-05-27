package mathdiffer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mathdiffer.Calculation;
import mathdiffer.DifferCalc;
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
        initializeIntSolv();
        initializeDiffer();
    }

    public void initializeIntSolv() {
        field_form.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                show_result();
            }
        });

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
                if (sl_N.getMax() >= Double.valueOf(tx_valueN.getText()) && sl_N.getMin() <= Double.valueOf(tx_valueN.getText())) {
                    property.set(true);
                    sl_N.setValue(Double.valueOf(tx_valueN.getText()));
                    tx_valueN.setStyle("-fx-background-color: white");
                } else {
                    tx_valueN.setStyle("-fx-background-color: red");
                }
            }
        });

        lb_valueEps.setText(String.valueOf((int) sl_eps.getValue()));
        lb_valueN.setText(String.valueOf((int) sl_N.getValue()));
        field_form.disableProperty().bind(field_a.textProperty().isEmpty().not().and(field_b.textProperty().isEmpty().not()).not());
        btn_enter.disableProperty().bind(field_form.disableProperty());

    }

    public void initializeDiffer() {
        DifferCalc.get("3*sin(2*y)+x", 0, 10, 2);
    }

    @FXML
    public void show_result() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ответ");
        alert.setHeaderText(field_form.getText());
        String a = field_a.getText();
        String b = field_b.getText();

        try {
            a = String.valueOf(Calculation.f(0, a));
            b = String.valueOf(Calculation.f(0, b));
        } catch (ArithmeticException arithmEx) {
            alert.setContentText("Ошибка ввода границ");
            alert.show();
            return;
        }

        alert.setContentText(Calculation.get(Double.valueOf(a), Double.valueOf(b), field_form.getText()));
        alert.show();
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText("");
        alert.setContentText("Авторы: \n"
                + "\tСтуденты ХНЭУ им. С. Кузнеца\n"
                + "\tБогдан Бида, Эдуард Белоусов\n"
                + "\t(bogdanbida.ua@gmail.com),(edikbelousov@gmail.com)\n"
                + "\t" + "intSolver" + "\n"
                + "\tПрограмма написана на JavaFX 8" + "\n"
                + "\t08.04.2018");

        alert.showAndWait();
    }

}
