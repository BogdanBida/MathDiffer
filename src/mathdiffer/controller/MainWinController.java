package mathdiffer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mathdiffer.Calculation;
import mathdiffer.DifferCalc;
import mathdiffer.MathDiffer;

public class MainWinController implements Initializable {

    @FXML
    private Tab int_tab;

    @FXML
    private TextField int_field_a;

    @FXML
    private TextField int_field_b;

    @FXML
    private TextField int_field_form;

    @FXML
    private Button int_btn_enter;

    @FXML
    private Label int_lb_valueEps;

    @FXML
    private Label int_lb_valueN;

    @FXML
    private TextField int_tx_valueN;

    @FXML
    private Slider int_sl_eps;

    @FXML
    private Slider int_sl_N;

    @FXML
    private Tab diff_tab;

    @FXML
    private AnchorPane diff_ach;

    @FXML
    private TextField diff_form;

    @FXML
    private TextField diff_x0;

    @FXML
    private TextField diff_y0;

    @FXML
    private TextField diff_end;
    
    @FXML TextField diff_h;

    NumberAxis diff_chart_xAxis = new NumberAxis();
    NumberAxis diff_chart_yAxis = new NumberAxis();
    XYChart<Double, Double> diff_chart = new LineChart(diff_chart_xAxis, diff_chart_yAxis);
    BooleanProperty int_property = new SimpleBooleanProperty(true);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeIntSolv();
        initializeDiffer();
    }

    public void initializeIntSolv() {
        int_field_form.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                show_result();
            }
        });

        int_sl_eps.valueProperty().addListener((observable, oldValue, newValue) -> {
            double tempNewValue = Math.round(newValue.doubleValue());
            int_sl_eps.setValue(tempNewValue);
            int_lb_valueEps.setText(String.valueOf((int) tempNewValue));
        });
        int_sl_N.valueProperty().addListener((observable, oldValue, newValue) -> {
            double tempNewValue = Math.round(newValue.doubleValue());
            int_sl_N.setValue(tempNewValue);
            int_lb_valueN.setText(String.valueOf((int) tempNewValue));
        });

        int_tx_valueN.visibleProperty().bind(int_property.not());
        int_lb_valueN.visibleProperty().bind(int_property);

        int_tx_valueN.managedProperty().bind(int_property.not());
        int_lb_valueN.managedProperty().bind(int_property);

        int_tx_valueN.textProperty().bindBidirectional(int_lb_valueN.textProperty());

        int_lb_valueN.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                int_property.set(false);
            }
        });

        int_tx_valueN.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (int_sl_N.getMax() >= Double.valueOf(int_tx_valueN.getText()) && int_sl_N.getMin() <= Double.valueOf(int_tx_valueN.getText())) {
                    int_property.set(true);
                    int_sl_N.setValue(Double.valueOf(int_tx_valueN.getText()));
                    int_tx_valueN.setStyle("-fx-background-color: white");
                } else {
                    int_tx_valueN.setStyle("-fx-background-color: red");
                }
            }
        });

        int_lb_valueEps.setText(String.valueOf((int) int_sl_eps.getValue()));
        int_lb_valueN.setText(String.valueOf((int) int_sl_N.getValue()));
        int_field_form.disableProperty().bind(int_field_a.textProperty().isEmpty().not().and(int_field_b.textProperty().isEmpty().not()).not());
        int_btn_enter.disableProperty().bind(int_field_form.disableProperty());
    }

    public void initializeDiffer() {
        diff_ach.getChildren().add(diff_chart);
        AnchorPane.setTopAnchor(diff_chart, 5.0);
        AnchorPane.setLeftAnchor(diff_chart, 5.0);
        AnchorPane.setRightAnchor(diff_chart, 5.0);
        AnchorPane.setBottomAnchor(diff_chart, 35.0);

        diff_form.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                getCommandDiffer();
            }
        });
    }

    public void getCommandDiffer() {
        double[][] array;
        String form = diff_form.getText();
        try {
            double x0 = Calculation.eval(Calculation.processingStr(diff_x0.getText()));
            double y0 = Calculation.eval(Calculation.processingStr(diff_y0.getText()));
            double end = Calculation.eval(Calculation.processingStr(diff_end.getText()));
            double h = Calculation.eval(Calculation.processingStr(diff_h.getText()));
            array = DifferCalc.get(form, x0, end, y0, h);
            buildLine(array, form);
        } catch (ArithmeticException expt) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.show();
        }
    }

    private void buildLine(double[][] list, String form) {
        XYChart.Series<Double, Double> line = new XYChart.Series<>();

        double lowerX = 10000;
        double upperX = -10000;
        double lowerY = 10000;
        double upperY = -10000;

        for (int i = 0; i < list[0].length; i++) {
//            if (arr[0] < lowerX) {
//                lowerX = arr[0];
//            }
//            if (arr[0] > upperX) {
//                upperX = arr[0];
//            }
//            if (arr[1] < lowerY) {
//                lowerY = arr[1];
//            }
//            if (arr[1] > upperY) {
//                upperY = arr[1];
//            }
            line.getData().add(new XYChart.Data<>(list[0][i], list[1][i]));
        }
        diff_chart_yAxis.setUpperBound(upperY);
        diff_chart_yAxis.setLowerBound(lowerY);

        diff_chart_xAxis.setUpperBound(upperX);
        diff_chart_xAxis.setLowerBound(lowerY);

        diff_chart_xAxis.setForceZeroInRange(false);
        diff_chart_yAxis.setForceZeroInRange(false);

        line.setName("(" + form + ")'");
        diff_chart.getData().setAll(line);
    }

    private double searchLowerNum(double[] arr) {
        double res = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < res) {

            } else {

            }
        }

        return res;
    }

    private double searchUpperNum() {
        return 0;
    }

    @FXML
    public void show_result() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ответ");
        alert.setHeaderText(int_field_form.getText());
        String a = int_field_a.getText();
        String b = int_field_b.getText();

        try {
            a = String.valueOf(Calculation.f(0, a));
            b = String.valueOf(Calculation.f(0, b));
        } catch (ArithmeticException arithmEx) {
            alert.setContentText("Ошибка ввода границ");
            alert.show();
            return;
        }

        alert.setContentText(Calculation.get(Double.valueOf(a), Double.valueOf(b), int_field_form.getText()));
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
                + "\tБогдан Бида(bogdanbida.ua@gmail.com),\n"
                + "\tЭдуард Белоусов(edikbelousov@gmail.com)\n"
                + "\nПрограмма написана на JavaFX 8" + "\n"
                + "\t29.05.2018");
        alert.showAndWait();
    }

}
