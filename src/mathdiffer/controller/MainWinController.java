package mathdiffer.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import mathdiffer.Calculation;

public class MainWinController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Calculation.get(0,2,"x*x+3*x"));
    }

}
