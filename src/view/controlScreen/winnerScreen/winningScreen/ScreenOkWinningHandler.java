package view.controlScreen.winnerScreen.winningScreen;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Created by alexp on 12-11-16.
 */
public class ScreenOkWinningHandler implements EventHandler<MouseEvent> {

    private final Node winningForm;
    private final Controller controller;

    public ScreenOkWinningHandler(Node winningForm, Controller controller){
        this.winningForm = winningForm;
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        this.winningForm.toBack();

        //TODO combat to history

        this.controller.combatToHistory();
    }
}
