package view.controlScreen.winnerScreen.winningScreen;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import view.controlScreen.winnerScreen.WinnerScreenController;

/**
 * Created by alexp on 12-11-16.
 */
public class ScreenOkWinningHandler implements EventHandler<MouseEvent> {

    private final WinnerScreenController winningForm;
    private final Controller controller;

    public ScreenOkWinningHandler(WinnerScreenController winningForm, Controller controller){
        this.winningForm = winningForm;
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        if(this.controller.initProperty().not().get())
            return;

        this.winningForm.getContainer().toBack();
        this.controller.setWinningCondition(this.winningForm.getSubmitedWinningOptions());

        this.controller.combatToHistory();
        this.controller.resetCombat();
    }
}
