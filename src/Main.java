import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.configurationScreen.ConfigScreenController;

import java.util.ResourceBundle;

/**
 * Created by alexp on 26-10-16.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Platform.setImplicitExit(false);
        primaryStage.setTitle("Judo Score Board");

        // Load resources
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.Resource");
        Font.loadFont(getClass().getResource("resource/font/Raleway-Light.ttf").toExternalForm(), 10);

        // Load the config screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/configurationScreen/Layout.fxml"), resourceBundle);
        primaryStage.setScene(new Scene(loader.load(), 800, 600));
        primaryStage.initStyle(StageStyle.DECORATED);

        ConfigScreenController configScreenController = loader.getController();
        configScreenController.init(primaryStage, Controller.getInstance());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
