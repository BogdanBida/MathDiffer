package mathdiffer.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class ManualWindowController implements Initializable {

    @FXML
    private WebView mainWin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        url = getClass().getResource("/mathdiffer/manual.html");
        mainWin.getEngine().load(url.toExternalForm());
        mainWin.contextMenuEnabledProperty().set(false);
        
    }    
    
}
