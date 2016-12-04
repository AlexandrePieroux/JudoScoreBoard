package view.controlScreen.historyScreen.historyHandler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.controlScreen.historyScreen.HistoryController;

/**
 * Created by alexandrepieroux on 4/12/16.
 */
public class HistoryOkHandler implements EventHandler<MouseEvent> {

    private HistoryController historyController;

    public HistoryOkHandler(HistoryController historyController){
        this.historyController = historyController;
    }

    @Override
    public void handle(MouseEvent event) {
        this.historyController.getContainer().toBack();
    }
}
