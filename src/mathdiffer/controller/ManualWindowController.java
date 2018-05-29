package mathdiffer.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.web.WebView;

public class ManualWindowController implements Initializable {

    @FXML
    private Tab int_tab;
    
    @FXML
    private WebView int_webView;

    @FXML
    private Tab diff_tab;

    @FXML
    private WebView diff_webView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        url = getClass().getResource("/mathdiffer/intManual.html");
        int_webView.getEngine().load(url.toExternalForm());
        int_webView.contextMenuEnabledProperty().set(false);
        
        url = getClass().getResource("/mathdiffer/diffManual.html");
        diff_webView.getEngine().load(url.toExternalForm());
        diff_webView.contextMenuEnabledProperty().set(false);
    }    
    
}
