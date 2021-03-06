 package mathdiffer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MathDiffer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MainWin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("intSolver");
        
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
