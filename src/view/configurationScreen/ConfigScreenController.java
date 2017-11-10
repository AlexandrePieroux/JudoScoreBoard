package view.configurationScreen;

import controller.Controller;
import view.ScreenExitHandler;
import view.configurationScreen.configHandler.ConfigOkHandler;
import view.configurationScreen.configHandler.ConfigSelectionHandler;
import controller.displayScreen.DisplayScreen;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Created by alexp on 28-10-16.
 */
public class ConfigScreenController {

    @FXML private Button settings;
    @FXML private Button help;
    @FXML private VBox vboxScreens;
    @FXML private Button ok;
    @FXML private Button exit;

    public void init(Stage primaryStage, Controller controller) {

        // Get evey screens available
        ObservableList<Screen> screensList = Screen.getScreens();

        if(screensList.size() == 1) {
            Label errorMsg = new Label(ResourceBundle.getBundle("resource.Resource")
                    .getString("ERROR_no_enough_screens"));
            errorMsg.getStyleClass().add("error_msg");
            this.vboxScreens.getChildren().add(errorMsg);
        } else {
            // Selection message
            ResourceBundle bundle = ResourceBundle.getBundle("resource.Resource");
            Label selectionMsg = new Label(bundle.getString("MSG_screen_selection"));
            selectionMsg.getStyleClass().add("msg_screen_selection");
            selectionMsg.setAlignment(Pos.TOP_CENTER);
            this.vboxScreens.getChildren().add(selectionMsg);

            // List of screens
            HBox screens = new HBox(5);
            DisplayScreen selectedScreen = new DisplayScreen();
            screens.getStyleClass().add("screens");
            screens.setAlignment(Pos.CENTER);
            ConfigSelectionHandler selectionHandler = new ConfigSelectionHandler(screens, selectedScreen);
            Screen primary = Screen.getPrimary();

            for (int i = 0; i < screensList.size(); ++i){
                Screen s = screensList.get(i);
                if(!s.equals(primary)){
                    Hyperlink display = new Hyperlink((int) s.getBounds().getWidth() + "x" + (int) s.getBounds().getHeight());
                    display.setId(Integer.toString(i));
                    display.getStyleClass().add("display");
                    display.setOnMouseClicked(selectionHandler);
                    screens.getChildren().add(display);
                }
            }
            this.vboxScreens.getChildren().add(screens);

            // Set the handler of the control buttons
            ConfigOkHandler screenOkHandler = new ConfigOkHandler(selectedScreen, primaryStage, controller);
            ScreenExitHandler exitHandler = new ScreenExitHandler();
            this.ok.setOnMouseClicked(screenOkHandler);
            this.exit.setOnMouseClicked(exitHandler);
        }
    }
}
