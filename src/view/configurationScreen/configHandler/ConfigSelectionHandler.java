package view.configurationScreen.configHandler;


import controller.displayScreen.DisplayScreen;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

/**
 * Created by alexp on 29-10-16.
 */
public final class ConfigSelectionHandler implements EventHandler<MouseEvent> {

    private HBox handlerScreens;
    private DisplayScreen selectedScreen;

    public ConfigSelectionHandler(HBox screens, DisplayScreen selectedScreen){
        this.selectedScreen = selectedScreen;
        this.handlerScreens = screens;
    }

    @Override
    public void handle(MouseEvent e){
        this.selectedScreen.setDisplayScreen(Screen.getScreens()
                .get(Integer.parseInt(((Hyperlink) e.getSource()).getId())));

        this.handlerScreens.getChildren().forEach((Node n) ->
            n.getStyleClass().remove("selected")
        );
        ((Hyperlink) e.getSource()).getStyleClass().add("selected");
    }
}
