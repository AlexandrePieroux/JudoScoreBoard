package view.controlScreen.mainScreen.controlHandler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.controlScreen.historyScreen.HistoryController;

/**
 * Created by alexandrepieroux on 4/12/16.
 */
public class ControlHistoryHandler implements EventHandler<MouseEvent> {

    private final HistoryController historyController;

    public ControlHistoryHandler(HistoryController historyController){
        this.historyController = historyController;
    }

    @Override
    public void handle(MouseEvent event) {
        this.historyController.getContainer().toFront();
    }
}
