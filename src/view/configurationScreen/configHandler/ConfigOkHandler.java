package view.configurationScreen.configHandler;

import controller.Controller;
import controller.displayScreen.DisplayScreen;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import view.controlScreen.mainScreen.ControlScreenController;
import view.displayScreen.DisplayScreenController;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by alexp on 29-10-16.
 */

// TODO the view must handle all of this
public class ConfigOkHandler implements EventHandler<MouseEvent> {

    private DisplayScreen selectedScreen;
    private Stage stage;
    private Controller controller;

    @Override
    public void handle(MouseEvent e){
        Screen screen = this.selectedScreen.getDisplayScreen();
        if(screen == null)
            return;

        // Create the control and the display screen
        lauchWindows(this.stage);
    }

    public ConfigOkHandler(DisplayScreen selectedScreen, Stage stage, Controller controller){
        super();
        this.stage = stage;
        this.selectedScreen = selectedScreen;
        this.controller = controller;
    }

    private void lauchWindows(Stage ownerStage){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.Resource");

        Stage controlScreen = new Stage();
        Stage displayScreen = new Stage();
        Scene displayScene;

        FXMLLoader displayScreenLoader = null;
        FXMLLoader controlScreenLoader = null;

        try {
            displayScreenLoader = new FXMLLoader(getClass().getResource("../../displayScreen/Layout.fxml"), resourceBundle);
            displayScene = new Scene(displayScreenLoader.load());
            displayScreen.setScene(displayScene);
            controlScreenLoader = new FXMLLoader(getClass().getResource("../../controlScreen/mainScreen/Layout.fxml"), resourceBundle);
            controlScreen.setScene(new Scene(controlScreenLoader.load()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // Load the display screen
        DisplayScreenController displayScreenController = displayScreenLoader.getController();
        displayScreenController.init(this.controller);
        displayScreen.initModality(Modality.APPLICATION_MODAL);
        displayScreen.initOwner(ownerStage);
        displayScreen.initStyle(StageStyle.UNDECORATED);
        Rectangle2D secondaryScreenbounds = this.selectedScreen.getDisplayScreen().getBounds();
        displayScreen.setX(secondaryScreenbounds.getMinX());
        displayScreen.setY(secondaryScreenbounds.getMinY());
        displayScreen.setFullScreen(true);

        // Load the control screen
        ControlScreenController controlScreenController = controlScreenLoader.getController();
        controlScreen.initModality(Modality.APPLICATION_MODAL);
        controlScreen.initOwner(ownerStage);
        controlScreen.initStyle(StageStyle.UNDECORATED);
        controlScreenController.init(this.controller);
        controlScreen.onCloseRequestProperty().setValue((WindowEvent e) -> Platform.exit());

        ownerStage.hide();
        //displayScreen.show();
        controlScreen.show();
    }
}
