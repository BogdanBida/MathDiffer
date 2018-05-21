package mathdiffer.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mathdiffer.Calculation;

public class MainWinController implements Initializable {

    @FXML
    private TextField field_a;

    @FXML
    private TextField field_b;

    @FXML
    private TextField field_f;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int a = 2; // получаем от пользователя
        int b = 0; //
        String f = "x^2+3*x"; //

        // выводим пользователю как ответ
        System.out.println(Calculation.get(a, b, f));
    }

}
