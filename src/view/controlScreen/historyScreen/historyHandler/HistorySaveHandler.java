package view.controlScreen.historyScreen.historyHandler;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexandrepieroux on 5/12/16.
 */
public class HistorySaveHandler implements EventHandler<MouseEvent> {

    private Controller controller;

    public HistorySaveHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        this.controller.historyToJSONFile();
    }
}
